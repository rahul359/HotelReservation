package com.rahul.program.search.service;

import com.rahul.program.search.model.Order;

import com.rahul.program.search.model.RoomCheckReponse;
import com.rahul.program.search.model.Room;
import com.rahul.program.search.model.SearchRequest;
import com.rahul.program.search.util.SearchRequestValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional

public class SearchRoomService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    //Returns all the available rooms
    public List<Room> getAllAvailableRooms(SearchRequest searchRequest) throws Exception {
        //Validations for search request
        SearchRequestValidations.isSearchRequestDetailsValid(searchRequest);

        //Fetch all the active orders by calling order-micro-service
        List<Order> orders = getActiveOrdersFromOrderMicroSer();

        //Fetch all the active rooms by calling room-micro-service
        List<Room> rooms = getActiveRoomsFromRoomMicroSer();

        //Removing rooms which have capacity less than sent no of guests
        rooms.removeIf(room -> room.getNoOfGuests() < searchRequest.getNoOfGuests());

        if (orders.size() == 0) {
            return rooms;
        }

        //Remove rooms from list based upon checkin and checkout
        removeRoomsBasedUponDate(rooms, searchRequest, orders);

        //Null check
        if (rooms.size() == 0) {
            throw new Exception("No rooms available");
        }

        //Response
        return rooms;
    }

    //Checks whether room is available to book or not and returns true or false
    public ResponseEntity<?> checkWhetherRoomIsAvailable(SearchRequest searchRequest, Long roomNo) throws Exception {
        try {
            //Fetch all the available rooms from getAllAvailableRooms method
            List<Room> rooms = (List<Room>) getAllAvailableRooms(searchRequest);

            //Check whether mentioned room id is present in list or not
           boolean status = false;

            //If rooms list isn't empty, now we will check whether mentioned room id is available or not
            for (Room room : rooms) {
                if (room.getRoomNo().equals(roomNo)) {
                    status = true;
                    break;
                }
            }

            //Response
            return ResponseEntity.status(HttpStatus.OK).body(new RoomCheckReponse(status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new RoomCheckReponse(false));
        }
    }

    //Removes rooms from list based upon logic
    public void removeRoomsBasedUponDate(List<Room> rooms, SearchRequest searchRequest, List<Order> orders) throws ParseException {
        //Formatting date in the form of yyyy-mm-dd
        SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
        Date checkInSent = sdfo.parse(searchRequest.getCheckIn());
        Date checkOutSent = sdfo.parse(searchRequest.getCheckOut());

        //Checking checkin and checkout condition
        for (Order order : orders) {
            Date checkIn = sdfo.parse(order.getCheckIn());
            Date checkOut = sdfo.parse(order.getCheckOut());

            //Checking checkIn and checkOut conditions
            if (checkInSent.after(checkIn) && checkInSent.before(checkOut)) {
                rooms.removeIf(room -> room.getRoomNo().equals(order.getRoomNo()));
            }
            if (checkOutSent.after(checkIn) && checkOutSent.before(checkOut)) {
                rooms.removeIf(room -> room.getRoomNo().equals(order.getRoomNo()));
            }
            if ((checkInSent.before(checkIn) || checkInSent.equals(checkIn)) &&
                    (checkOutSent.after(checkOut) || checkOutSent.equals(checkOut))) {
                rooms.removeIf(room -> room.getRoomNo().equals(order.getRoomNo()));
            }
        }
    }

    //Gets list of active rooms from room-microservice
    public List<Room> getActiveRoomsFromRoomMicroSer() throws Exception {
        try {
            //Url set
            String url = "http://ROOM-SERVICE/room/active";

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            //Sending request
            ResponseEntity<List<Room>> responseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Room>>() {
                    }, true);

            //If success, return value
            return responseEntity.getBody();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Gets list of active orders from order-microservice
    public List<Order> getActiveOrdersFromOrderMicroSer() throws Exception {
        try {
            //Url set
            String url = "http://BOOKING-SERVICE/api/hotel/book/get/order/active";

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            //Sending request
            ResponseEntity<List<Order>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {
                    });

            return responseEntity.getBody();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
