package com.rahul.program.search.util;

import com.rahul.program.search.model.SearchRequest;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SearchRequestValidations {

    public static void isSearchRequestDetailsValid(SearchRequest searchRequest) throws Exception {
        if (!isNoOfGuestsValid(searchRequest.getNoOfGuests())) {
            throw new Exception("No of guests must be greater than 0");
        }
        isCheckInAndCheckOutValid(searchRequest.getCheckIn(), searchRequest.getCheckOut());
    }

    public static boolean isNoOfGuestsValid(int noOfGuests) {
        return noOfGuests > 0;
    }

    public static void isCheckInAndCheckOutValid(String checkIn, String checkOut) throws Exception {
        //Calling valid date function which check whether data is valid or not.Ex: Feb 30 is Invalid
        if (!ValidDate.validDate(checkIn)) {
            throw new Exception("Checkin date is INVALID");
        }
        if (!ValidDate.validDate(checkOut)) {
            throw new Exception("Checkout date is INVALID");
        }

        //Setting date format of type dd/MM/yyyy
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Converting type String to type DATE
        Date checkInSent = dateFormat.parse(checkIn);
        Date checkOutSent = dateFormat.parse(checkOut);
        Date date = Calendar.getInstance().getTime();

        //Today's date
        Date todayDate = dateFormat.parse(dateFormat.format(date));

        //CheckIn shouldn't be before today date
        if (!(checkInSent.equals(todayDate) || checkInSent.after(todayDate))) {
            throw new Exception("Checkin date must be today and onwards.");
        }
        //Checkout and checkIn can't be same
        if ((checkOutSent.equals(checkInSent))) {
            throw new Exception("CheckIn and checkout can't be same");
        }
        //Checkout must be after today date
        if (checkOutSent.before(todayDate) || checkOutSent.equals(todayDate)) {
            throw new Exception("Checkout date must be from tomorrow.");
        }
        //CheckOut shouldn't be after checkIn
        if (checkInSent.after(checkOutSent)) {
            throw new Exception("Checkout date should be after checkin date.");
        }
    }
}
