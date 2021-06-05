package com.rahul.program.employee.model;

import javax.validation.constraints.Max;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rahul.program.employee.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="employee")
public class Employee {
	
	
	@Id
	@Min(value =1000)
	private Long registeredNo;
	private String password;
	private String email;
	@NotNull(message = "Cannot be null")
	private String name;
	@NotNull(message = "Cannot be null")
    private String designation;
	@NotNull(message = "Cannot be null")
	@Min(value = 25, message = "Age must be greater or equal to 25")
	@Max(value=45, message = "Age must be less than 45")
	private int age;
	@NotNull(message = "Cannot be null")
	@Min(value = 20000, message = "Age must be greater or equal to 20000")
	@Max(value=45000, message = "Age must be less than 45000")
    private int salary;
	@NotNull(message = "Cannot be null")
    private boolean status;

	
	
}
