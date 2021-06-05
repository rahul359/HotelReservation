package com.rahul.program.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchRequest {
    private String checkIn;
    private String checkOut;
    private int noOfGuests;
}
