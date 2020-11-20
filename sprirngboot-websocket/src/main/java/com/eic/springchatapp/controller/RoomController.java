package com.eic.springchatapp.controller;

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
import com.eic.springchatapp.model.MRequest;
import com.eic.springchatapp.model.Room;
import com.eic.springchatapp.repository.RoomRepository;



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
	ResponseEntity<?>  addRoom(@RequestBody MRequest mRequest ){
		
		Room newRoom = new Room(mRequest.getRoomname(),mRequest.getPassword());
		roomRepository.insert(newRoom);
		
		
		
		return joinRoom(mRequest);
			
	}
	
	
	//Adding the user to room
	@PostMapping("/joinRoom")
	ResponseEntity<?>  joinRoom(@RequestBody MRequest mRequest){
		
		
		Room room = roomRepository.findByNameAndPassword(mRequest.getRoomname(), mRequest.getPassword());
		if(room != null) {
			
			
			room.getUsers().add(mRequest.getUsername());
			roomRepository.save(room);
			return ResponseEntity.ok().body(room);
			
			
		}else {
			
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			

		}
	}
	
	
		//Adding the user to room
		@PostMapping("/getUsersInRoom")
		ResponseEntity<?>  getUsersInRoom(@RequestBody MRequest mRequest){
			
			
			Room room = roomRepository.findByNameAndPassword(mRequest.getRoomname(), mRequest.getPassword());
			
			if(room != null) {
				
				
				return ResponseEntity.ok().body(room.getUsers());						
			}else {
				
				
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
			}
			
		}
		
}
