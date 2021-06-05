package com.rahul.program.rooms.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="rooms")
public class Room {
	@Id
	@NotNull(message = "Must not be null")
	@Min(value = 101)
	@Max(value = 400)
    private Long roomNo;
	@NotNull(message = "Must not be null")
    private String type;
    @NotNull(message = "Must not be null")
    private int noOfGuests;
    @NotNull(message = "Must not be null")
    private float pricePerDay;
    private boolean status;
	
 

}


