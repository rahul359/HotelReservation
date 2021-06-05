package com.rahul.program.order.model;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "orders")
public class Order {
    @Id
    private String _id;
    @NotNull(message = "Cannot be null")
    private String checkIn;
    @NotNull(message = "Cannot be null")
    private String checkOut;
    @Max(value=6, message = "No of guests must be less than 7")
    private int noOfGuests;
    private Guest guest;
    private int noOfDays;
    private Long roomNo;
    private AmountDetails amountDetails;
    private String bookedAt;
    private String paymentMode;
    private boolean status;
}