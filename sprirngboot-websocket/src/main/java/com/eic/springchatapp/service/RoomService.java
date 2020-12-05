package com.eic.springchatapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eic.springchatapp.api.model.GenericResponse;
import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;
import com.eic.springchatapp.api.repository.UserRepository;
import com.eic.springchatapp.model.User;





@Service
public class RoomService {

	private final int MAX_USER_CAPACITY = 20;

	@Autowired
	RoomRepository roomRepository;

	

	@Autowired
	UserRepository userRepository;
	
	
	public GenericResponse<?> createRoom(String userName, String roomName, String roomPassword) {

		if (roomRepository.findByName(roomName) == null) {

			Room newRoom = new Room(roomName, roomPassword);

			roomRepository.save(newRoom);

			return addUserToRoom(userName, roomName, roomPassword);

		} else {

			return new GenericResponse<String>(HttpStatus.CONFLICT, "Duplicate Room Name");

		}
	}

	public GenericResponse<?> addUserToRoom(String userName, String roomName, String roomPassword) {

		Room room = roomRepository.findByNameAndPassword(roomName, roomPassword);

		if (room == null) {
			return new GenericResponse<String>(HttpStatus.FORBIDDEN, "Could Not Find A Room  With Given Credentials");

		} else if (room.getUsers().contains(userName)) {
			return new GenericResponse<String>(HttpStatus.CONFLICT, "User Already In The Room");

		} else if (room.getUsers().size() >= MAX_USER_CAPACITY) {
			return new GenericResponse<String>(HttpStatus.NOT_ACCEPTABLE, "Room Capacity Is Full");

		} else {
			
			room.getUsers().add(userName);
			roomRepository.save(room);
			
			User user = userRepository.findByName(userName);
			user.getRooms().add(roomName);
			userRepository.save(user);
			
			return new GenericResponse<Room>(HttpStatus.OK, room);

		}
	}

	public GenericResponse<?> getUsesInRoom(String roomName) {
		Room room = roomRepository.findByName(roomName);

		if (room == null) {
			return new GenericResponse<String>(HttpStatus.FORBIDDEN, "Could Not Find A Room With Given Credentials");

		} else {
			List<String> userList = room.getUsers();
			return new GenericResponse<List<String>>(HttpStatus.OK, userList);

		}

	}

	public GenericResponse<?> deleteRoom(String roomName, String roomPassword) {

		Room room = roomRepository.findByNameAndPassword(roomName, roomPassword);

		if (room == null) {
			return new GenericResponse<String>(HttpStatus.FORBIDDEN,
					"Could Not Find A Room The With Given Credentials");

		} else {

			roomRepository.delete(room);
			return new GenericResponse<Room>(HttpStatus.OK, room);

		}
	}

	public GenericResponse<?> deleteUserFromRoom(String userName, String roomName, String roomPassword) {

		Room room = roomRepository.findByNameAndPassword(roomName, roomPassword);

		if (room == null) {
			return new GenericResponse<String>(HttpStatus.FORBIDDEN, "Could Not Find The Room  With Given Credentials");

		} else if (!room.getUsers().contains(userName)) {

			return new GenericResponse<String>(HttpStatus.FORBIDDEN, "User Not In The Roon");

		} else {
			room.getUsers().remove(room.getUsers().indexOf(userName));
			roomRepository.save(room);
			return new GenericResponse<Room>(HttpStatus.OK, room);

		}
	}
	
	public boolean isUserInRoom(String roomId ,String username) {
		
		Room room = roomRepository.findOneById(roomId);
		

		
		if(room == null) {
			return false;
		}else if (!room.getUsers().contains(username)) {
			return false;
		}else {
			return true;	
		}
	}
	
	public void disconnectUser(String username) {
		
		
		User user = userRepository.findByName(username);
		Room room;
		
		
		for (String roomname : user.getRooms() ) {
			room = roomRepository.findByName(roomname); 
			room.getUsers().remove(username);
			
			
			roomRepository.save(room);
		}
		user.getRooms().clear();
		userRepository.save(user);
	}
}
