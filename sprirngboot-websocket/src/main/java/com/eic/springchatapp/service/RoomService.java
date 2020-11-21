package com.eic.springchatapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eic.springchatapp.api.model.GenericResponse;
import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;

@Service
public class RoomService {

	private final int MAX_USER_CAPACITY = 20;

	@Autowired
	RoomRepository roomRepository;

	public GenericResponse<Room> createRoom(String roomName, String roomPassword) {

		
		if (roomRepository.findByName(roomName) == null) {

			Room newRoom = new Room(roomName, roomPassword);

			roomRepository.save(newRoom);

			return new GenericResponse<Room>(HttpStatus.CREATED, "Room Created", newRoom);

		} else {

			return new GenericResponse<Room>(HttpStatus.CONFLICT, "Duplicate Room Name", null);

		}
	}

	public GenericResponse<Room> addUserToRoom(String userName, String roomName, String roomPassword) {

		Room room = roomRepository.findByNameAndPassword(roomName, roomPassword);

		if (room == null) {
			return new GenericResponse<Room>(HttpStatus.FORBIDDEN, "Could Not Find The With Given Credentials", null);

		} else if (room.getUsers().contains(userName)) {
			return new GenericResponse<Room>(HttpStatus.CONFLICT, "User Already In Room", null);

		} else if (room.getUsers().size() >= MAX_USER_CAPACITY) {
			return new GenericResponse<Room>(HttpStatus.NOT_ACCEPTABLE, "Room Capacity Is Full", null);

		} else {

			room.getUsers().add(userName);
			roomRepository.save(room);
			return new GenericResponse<Room>(HttpStatus.OK, "User jonied " + roomName, room);

		}
	}

	
	public GenericResponse<List<String>> getUsesInRoom(String roomName){
		Room room = roomRepository.findByName(roomName);
		
		if (room == null) {
			return new GenericResponse<List<String>>(HttpStatus.FORBIDDEN, "Could Not Find The With Given Credentials", null);

		}else {
			List<String> userList = room.getUsers();
			return new GenericResponse<List<String>>(HttpStatus.FORBIDDEN, "Users List Found", userList);

		}
		
	}
}
