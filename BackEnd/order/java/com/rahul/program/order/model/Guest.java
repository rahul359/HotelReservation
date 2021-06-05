package com.rahul.program.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guest {
    private String name;
    private String gender;
    private String company;
    @Email
    private String email;
    private String mobileNo;
    private String address;
    private boolean status;
}
