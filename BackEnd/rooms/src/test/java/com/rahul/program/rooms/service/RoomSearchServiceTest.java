package com.rahul.program.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rahul.program.rooms.model.Room;
import com.rahul.program.rooms.repository.RoomRepository;

class RoomSearchServiceTest {

	@Mock
	RoomRepository repo;
	
	@InjectMocks
	RoomSearchService service;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetAllActiveRooms() {
		Room room1=new Room();
		room1.setRoomNo((long)101);
		room1.setType("Deluxe");
		room1.setNoOfGuests(4);
		room1.setStatus(true);
		room1.setPricePerDay(1500);
		Room room2=new Room();
		room2.setRoomNo((long)102);
		room2.setType("Super Deluxe");
		room2.setNoOfGuests(5);
		room2.setStatus(true);
		room2.setPricePerDay(2500);
		List<Room> list=new ArrayList<Room>();
		list.add(room1);
		list.add(room2);
		when( repo.findAllByStatus(true)).thenReturn(list);	       
		assertThat(service.getAllActiveRooms()).isEqualTo(list);
	}

}
