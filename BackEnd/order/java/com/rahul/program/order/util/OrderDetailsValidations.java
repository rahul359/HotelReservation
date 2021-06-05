package com.rahul.program.order.util;




import com.rahul.program.order.model.Order;

import com.rahul.program.order.model.InvalidDataException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDetailsValidations {
    
    public static void isOrderDetailsValid(Order order) throws Exception {
     
       // if (order.get_id() != null) {
         //   throw new Exception("Id isn't required");
        //}
        if (order.getCheckIn() == null || order.getCheckOut() == null) {
            throw new InvalidDataException(" Please Provide CheckIn and checkOut dates ");
        }
       
        
        if (order.getRoomNo() == null) {
            throw new InvalidDataException("Room id is required");
        }
      

      
        isCheckInAndCheckOutValid(order.getCheckIn(), order.getCheckOut());
    }


   
    public static void isCheckInAndCheckOutValid(String checkIn, String checkOut) throws Exception {
        
        if (ValidDate.validDate(checkIn)) {
            throw new Exception("Checkin date is INVALID");
        }
        if (ValidDate.validDate(checkOut)) {
            throw new Exception("Checkout date is INVALID");
        }

       
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  
        Date checkInSent = dateFormat.parse(checkIn);
        Date checkOutSent = dateFormat.parse(checkOut);
        Date date = Calendar.getInstance().getTime();

        
        Date todayDate = dateFormat.parse(dateFormat.format(date));

      
        if (!(checkInSent.equals(todayDate) || checkInSent.after(todayDate))) {
            throw new Exception("Invalid Checkin date");
        }
        
        if ((checkOutSent.equals(checkInSent))) {
            throw new Exception("CheckIn and checkout Must be different");
        }
     
        if (checkOutSent.before(todayDate) || checkOutSent.equals(todayDate)) {
            throw new Exception("Invalid Checkout.");
        }
    
        if (checkInSent.after(checkOutSent)) {
            throw new Exception("Invalid Checkout.");
        }
    }


 
}
