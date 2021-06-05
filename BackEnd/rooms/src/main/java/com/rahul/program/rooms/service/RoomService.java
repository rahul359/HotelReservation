package com.rahul.program.rooms.service;

import javax.validation.ConstraintViolationException;


import com.rahul.program.rooms.exception.RoomException;
import com.rahul.program.rooms.model.Room;
import java.util.List;

public interface RoomService {
	
	public void createRoom(Room room) throws ConstraintViolationException,  RoomException;
	
	public List<Room> getAllRooms();
	
	public Room getSingleRoom(Long roomNo) throws RoomException;
	
	public void updateRoom(Long roomNo, Room room) throws RoomException;
	
	public void deleteRoomById(Long roomNo) throws RoomException;

	
	
	

}
