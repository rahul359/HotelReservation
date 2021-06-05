package com.rahul.program.order.service;

import com.rahul.program.order.model.AmountDetails;


import com.rahul.program.order.model.Guest;
import com.rahul.program.order.model.Order;
import com.rahul.program.order.model.RoomCheckReponse;
import com.rahul.program.order.model.Room;
import com.rahul.program.order.model.SearchRequest;
import com.rahul.program.order.rabbitmq.MessagingConfig;
import com.rahul.program.order.repository.OrderRepository;
import com.rahul.program.order.util.OrderDetailsValidations;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service

public class AddOrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    @Autowired
    private RabbitTemplate template;

    
    public ResponseEntity<?> addOrder(Order order) throws Exception {
        
        OrderDetailsValidations.isOrderDetailsValid(order);

       
        setDetailsAndCheckIsRoomAvailable(order);

      
         Order orderAfterSave=orderRepository.save(order);
         
         //Email response is handled by using rabbit mq
         template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderAfterSave);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "Room is booked successfully"));
    }

   
    private void setDetailsAndCheckIsRoomAvailable(Order order) throws Exception {
        
        Room room = getRoomDetailsFromRoomMicroService(order.getRoomNo());

        
        if (room == null) {
            throw new Exception("Invalid room No");
        }

        
        if (!(order.getNoOfGuests() <= room.getNoOfGuests())) {
            throw new Exception("Room has max capacity of "
                    + room.getNoOfGuests() + " guests. But sent data contains "
                    + order.getNoOfGuests() + " guests");
        }

        
        SearchRequest searchRequest = new
                SearchRequest(order.getCheckIn(), order.getCheckOut(), order.getNoOfGuests());
        if (!checkRoomWhetherItIsAvailableOrNot(order.getRoomNo(), searchRequest).isStatus()) {
            throw new Exception("Room is not available to book ");
        }

        
        setAmountDetails(room, order);

     
        setDatePaymentModeAndStatus(order);
    }


    public void setAmountDetails(Room room, Order order) throws Exception {
      
        order.setNoOfDays(diffBetweenDates(order.getCheckIn(), order.getCheckOut()));

        
        order.setRoomNo(room.getRoomNo());

     
        float pricePerDay = room.getPricePerDay();
        int noOfDays = order.getNoOfDays();
        float tax = 18;
        float discount = 10;

        
        float amountForDays = pricePerDay * noOfDays;

        
        float amountAfterTaxAndDiscount = (amountForDays)
                + ((amountForDays * tax) / 100) - ((amountForDays * discount) / 100);

       
        float amountSave = amountForDays - amountAfterTaxAndDiscount;
        AmountDetails amountDetails = new AmountDetails(pricePerDay,
                amountForDays, tax, discount, amountSave,
                amountAfterTaxAndDiscount, amountAfterTaxAndDiscount);

  
        order.setAmountDetails(amountDetails);
    }

 
    private void setDatePaymentModeAndStatus(Order order) {
       

        
        order.setStatus(true);

        //Guest details to UpperCase
        Guest guest = new Guest(order.getGuest().getName().toUpperCase(),
                order.getGuest().getGender().toUpperCase(), order.getGuest().getCompany().toUpperCase(),
                order.getGuest().getEmail().toLowerCase(),
                order.getGuest().getMobileNo(), order.getGuest().getAddress().toUpperCase(),
                true
        );
        order.setGuest(guest);

        //Setting booked time as current time
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date date = Calendar.getInstance().getTime();
        order.setBookedAt(dateFormat.format(date));
    }

    //Calculates integer value of difference between dates
    public int diffBetweenDates(String checkIn, String checkOut) throws ParseException {
        //Setting format of date
        SimpleDateFormat sdlc = new SimpleDateFormat("dd/MM/yyyy");

        //Converting string to date format
        Date checkInDate = sdlc.parse(checkIn);
        Date checkOutDate = sdlc.parse(checkOut);

        //Difference between dates
        long difference = checkOutDate.getTime() - checkInDate.getTime();

        //Response
        return (int) (difference / (1000 * 60 * 60 * 24));
    }


    

    //Calls search-microservice and makes sure that room is not booked once again
    public RoomCheckReponse checkRoomWhetherItIsAvailableOrNot(Long roomNo, SearchRequest searchRequest) throws Exception {
        try {
            //Url set
            String url = "http://SEARCH-SERVICE/room/check/{roomNo}";

            HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);

            //Sending request
            ResponseEntity<RoomCheckReponse> responseEntity = restTemplate
                    .exchange(url, HttpMethod.POST, entity, RoomCheckReponse.class, roomNo);

            //If success, return value
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Calls room-micro service and fetches room details by room id
    public Room getRoomDetailsFromRoomMicroService(Long roomNo) throws Exception {
        try {
            //Url set
            String url = "http://ROOM-SERVICE/room/{roomNo}";

            HttpEntity<String> entity = new HttpEntity<>(headers);

            //Sending get request
            ResponseEntity<Room> responseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, entity, Room.class, roomNo);

            //If success, return value
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
