package com.rahul.program.search.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String _id;
    private String checkIn;
    private String checkOut;
    private int noOfGuests;
    private Guest guest;
    private int noOfDays;
    private Long roomNo;
    private AmountDetails amountDetails;
    private String bookedAt;
    private String paymentMode;
    private String paymentStatus;
    private boolean status;

	}



