package com.rahul.program.order.exception;


public class OrderException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Order With"+id+"not found";
	}

	public static String RoomAlreadyExists() {
		return "Order already Exists";
	}
	
}
