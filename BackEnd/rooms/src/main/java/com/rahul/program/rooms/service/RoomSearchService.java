package com.rahul.program.rooms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rahul.program.rooms.repository.RoomRepository;
import com.rahul.program.rooms.model.Room;

@Service
public class RoomSearchService {
	  @Autowired
	    private RoomRepository roomRepository;
	
    
    public ResponseEntity<?> getAllActiveRooms() {
       
        List<Room> orders = roomRepository.findAllByStatus(true);

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }


}
