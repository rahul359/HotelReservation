package com.rahul.program.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rahul.program.rooms.exception.RoomException;
import com.rahul.program.rooms.model.Room;
import com.rahul.program.rooms.repository.RoomRepository;

class TestRoomServiceImpl {
	
	@Mock
	RoomRepository repo;
	
	@InjectMocks
	RoomServiceImpl service;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	void testCreateRoom() throws ConstraintViolationException, RoomException {
		Room room=new Room();
		room.setRoomNo((long)101);
		room.setType("Deluxe");
		room.setNoOfGuests(4);
		room.setStatus(true);
		room.setPricePerDay(1500);
		Room r=room;
		when(repo.save(room)).thenReturn(r);
		assertThat(service.createRoom(r)).isEqualTo(room);
	}

	@Test
	void testGetAllRooms() {
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
		when( repo.findAll()).thenReturn(list);	       
		assertThat(service.getAllRooms()).isEqualTo(list);
	}

	@Test
	void testGetSingleRoom() throws RoomException {
		Room room=new Room();
		room.setRoomNo((long)101);
		room.setType("Deluxe");
		room.setNoOfGuests(4);
		room.setStatus(true);
		room.setPricePerDay(1500);
		when(repo.findById( (long) 101)).thenReturn(Optional.of(room));
		Room r=service.getSingleRoom((long) 101);
		assertNotNull(r);
		assertEquals("Deluxe",r.getType());
	}

	@Test
	void testUpdateRoom() throws RoomException {
		Room room=new Room();
		room.setRoomNo((long)101);
		room.setType("Deluxe");
		room.setNoOfGuests(4);
		room.setStatus(true);
		room.setPricePerDay(1500);
		repo.save(room);
		room.setPricePerDay(1900);
		when(repo.findById( (long) 101)).thenReturn(Optional.of(room));
		service.updateRoom((long) 101, room);
		assertEquals(1900,room.getPricePerDay());
	}

	@Test
	void testDeleteRoomById() throws RoomException {
		Room room=new Room();
		room.setRoomNo((long)101);
		room.setType("Deluxe");
		room.setNoOfGuests(4);
		room.setStatus(true);
		room.setPricePerDay(1500);
		repo.save(room);
		when(repo.findById((long) 101)).thenReturn(Optional.of(room));
		assertThat(service.deleteRoomById((long) 101)).isEqualTo("Success");
	}

}
