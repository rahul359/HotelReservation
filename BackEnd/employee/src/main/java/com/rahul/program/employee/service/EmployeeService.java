package com.rahul.program.employee.service;

import java.util.List;


import javax.validation.ConstraintViolationException;

import com.rahul.program.employee.exception.EmployeeException;
import com.rahul.program.employee.model.Employee;

public interface EmployeeService {

	
	public Employee createEmployee(Employee employee) throws ConstraintViolationException,  EmployeeException;
	
	public List<Employee> getAllEmployee();
	
	public Employee getSingleEmployee(Long id) throws EmployeeException;
	
	public Employee updateEmployee(Long id, Employee employee) throws EmployeeException;
	
	public String deleteEmployeeById(Long id) throws EmployeeException;
		
	
}
