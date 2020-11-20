package com.eic.springchatapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eic.springchatapp.api.model.GenericRequest;
import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;



/*  	TODO
 * 
 * -Validation
 * 
 * 
 * */

@RestController
@RequestMapping("room")
public class RoomController {

	
	@Autowired
	RoomRepository roomRepository;
	
	//Adding a new room and inserting user in it 
	@PostMapping("/addRoom")
	ResponseEntity<?>  addRoom(@RequestBody GenericRequest genericRequest ){
		
		Room newRoom = new Room(genericRequest.getRoomname(),genericRequest.getPassword());
		roomRepository.insert(newRoom);
		
		
		
		return joinRoom(genericRequest);
			
	}
	
	
	//Adding the user to room
	@PostMapping("/joinRoom")
	ResponseEntity<?>  joinRoom(@RequestBody GenericRequest genericRequest){
		
		
		Room room = roomRepository.findByNameAndPassword(genericRequest.getRoomname(), genericRequest.getPassword());
		if(room != null) {
			
			
			room.getUsers().add(genericRequest.getUsername());
			roomRepository.save(room);
			return ResponseEntity.ok().body(room);
			
			
		}else {
			
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			

		}
	}
	
	
		//Adding the user to room
		@PostMapping("/getUsersInRoom")
		ResponseEntity<?>  getUsersInRoom(@RequestBody GenericRequest genericRequest){
			
			
			Room room = roomRepository.findByNameAndPassword(genericRequest.getRoomname(), genericRequest.getPassword());
			
			if(room != null) {
				
				
				return ResponseEntity.ok().body(room.getUsers());						
			}else {
				
				
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			}
			
		}
		
}
