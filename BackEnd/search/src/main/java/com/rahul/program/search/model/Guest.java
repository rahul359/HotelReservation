package com.rahul.program.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guest {
    private String name;
    private String gender;
    private String company;
    private String email;
    private Long mobileNo;
    private String address;
    private boolean status;
}
