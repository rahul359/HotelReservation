package com.rahul.program.order.service;

import com.rahul.program.order.model.AmountDetails;

import com.rahul.program.order.model.Order;
import com.rahul.program.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
@CrossOrigin(origins="http://localhost:3000")
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    

    //To make order status false, customer can checkout once there is no due amount
    public ResponseEntity<?> checkOut(String orderId) throws Exception {
        //Fetch order details by Id
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order id is Invalid"));

        //Check whether order is active or not and payment status
        checkStatusAndPaymentStatus(order);

        //Check today date and guest checkout date and find days gap if gap is present
        int noOfDaysGap = checkTodayDateAndCheckOut(order.getCheckOut());

        //If no of days gap=0, then customer is checking out at correct time
        if (noOfDaysGap == 0) {
            //Set values for correct time checkout
            order.setStatus(false);
            //storing data to DB
            orderRepository.save(order);
        }

        //If days gap is > 0 , then customer is delay in checkout
        if (noOfDaysGap > 0) {
            //Set values for delay in checkout
            setDelayValues(order, noOfDaysGap);
            orderRepository.save(order);
            throw new Exception("There is a delay of " + noOfDaysGap + " days. Pay extra amount of Rs: "
                    + order.getAmountDetails().getDueAmount() + " to checkout");
        }

        //Response
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Checkout successful."));
    }

    //Delete order by status
    public ResponseEntity<?> deleteOrderById(String orderId) throws Exception {
        //Fetch order by id
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("No order found"));

        //Check status of order
        if (order.isStatus()) {
            throw new Exception("Order is still active. It is available to delete once user check out.");
        }

        //if status is false
        orderRepository.deleteById(orderId);

        //Response
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message", "Order details are deleted successfully for id: " + orderId));
    }

    //Calculates the delay in checkout by considering today date
    private void setDelayValues(Order order, int noOfDaysGap) {
        //update amount details and extra amount to pay
        setAmountDetails(order, noOfDaysGap);


        //update no of days
        order.setNoOfDays((order.getNoOfDays()) + noOfDaysGap);

        //Update new checkout
        //Setting date format of type dd/MM/yyyy
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Converting type String to type DATE
        Date date = Calendar.getInstance().getTime();
        String todayDate = dateFormat.format(date);

        order.setCheckOut(todayDate);

    }

    //Set amount details based upon delay no of days
    private void setAmountDetails(Order order, int noOfDaysGap) {
        //get price of room per day
        float pricePerDay = order.getAmountDetails().getAmountPerDay();

        //tax
        float tax = order.getAmountDetails().getTax();

        //discount
        float discount = order.getAmountDetails().getDiscount();

        //amount for mentioned days gap
        float amountForDays = pricePerDay * noOfDaysGap;

        //amount after adding tax and discount
        float amountAfterTaxAndDiscount = amountForDays
                + ((amountForDays * tax) / 100)
                - ((amountForDays * discount) / 100);

        //actual total amount including old and new
        float totalAmountForDays = order.getAmountDetails().getAmountForDays() + amountForDays;

        //total final amount he/she has to pay including old and new
        float totalFinalAmount = order.getAmountDetails().getFinalAmount() + amountAfterTaxAndDiscount;

        //total save including old and new
        float totalSave = totalAmountForDays - totalFinalAmount;

        //creating object of amount details with updated data
        AmountDetails amountDetails = new AmountDetails(pricePerDay,
                totalAmountForDays, tax, discount, totalSave,
                totalFinalAmount, amountAfterTaxAndDiscount);

        order.setAmountDetails(amountDetails);
    }

    //If status of order is false and amount is due, then checkout isn't possible.
    public void checkStatusAndPaymentStatus(Order order) throws Exception {
        //check whether status is true/false
        if (!order.isStatus()) {
            throw new Exception("Already checked out");
        }
       
    }

    //Finds the diff between dates
    public int checkTodayDateAndCheckOut(String checkOut) throws ParseException {
        int noOfDays = 0;
        //Setting date format of type dd/MM/yyyy
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Converting type String to type DATE
        Date checkOutSent = dateFormat.parse(checkOut);
        Date date = Calendar.getInstance().getTime();

        //Today's date
        Date todayDate = dateFormat.parse(dateFormat.format(date));

        //check whether dates are equal or not
        if (checkOutSent.before(todayDate)) {
            noOfDays = diffBetweenDates(todayDate, checkOutSent);
        }
        return noOfDays;
    }

    public int diffBetweenDates(Date todayDate, Date checkOut) {
        //Difference between dates
        long difference = todayDate.getTime() - checkOut.getTime();

        //Response
        return (int) (difference / (1000 * 60 * 60 * 24));
    }
}
