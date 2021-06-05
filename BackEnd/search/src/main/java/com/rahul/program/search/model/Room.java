package com.rahul.program.search.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {

    private String type;
    private Long roomNo;
    private int noOfGuests;
    private float pricePerDay;
    private boolean status;
 

}


