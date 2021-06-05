package com.rahul.program.rooms.exception;

public class RoomException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomException(String message) {
		super(message);
	}
	
	public static String NotFoundException(Long roomNo) {
		return "Room With"+roomNo+"not found";
	}

	public static String RoomAlreadyExists() {
		return "Room already Exists";
	}
	
}
