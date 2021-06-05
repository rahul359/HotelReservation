package com.rahul.program.employee.exception;


public class EmployeeException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeException(String message) {
		super(message);
	}
	
	public static String NotFoundException(Long registeredNo) {
		return "Employee With"+registeredNo+"not found";
	}

	public static String EmployeeAlreadyExists() {
		return "Employee already Exists";
	}
	
}