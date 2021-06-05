package com.rahul.program.search.controller;

import com.rahul.program.search.model.Room;
import com.rahul.program.search.model.SearchRequest;
import java.util.List;


import com.rahul.program.search.service.SearchRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class SearchRoomController {
    @Autowired
    private SearchRoomService searchRoomService;

    //Returns all the available rooms
    @PostMapping("/room")
    public List<Room> getAvailableRooms(@RequestBody SearchRequest searchRequest) throws Exception {
        
            return searchRoomService.getAllAvailableRooms(searchRequest);
        
    }

    //Returns true or false for whether room is available or not
    @PostMapping("/room/check/{roomNo}")
    public ResponseEntity<?> checkIsRoomAvailable(@RequestBody SearchRequest searchRequest, @PathVariable Long roomNo) {
        try {
            return searchRoomService.checkWhetherRoomIsAvailable(searchRequest, roomNo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}

