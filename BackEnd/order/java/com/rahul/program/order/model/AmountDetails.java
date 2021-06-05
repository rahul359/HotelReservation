package com.rahul.program.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AmountDetails {
    private float amountPerDay;
    private float amountForDays;
    private float tax;
    private float discount;
    private float savingAmount;
    private float finalAmount;
    private float dueAmount;
}
