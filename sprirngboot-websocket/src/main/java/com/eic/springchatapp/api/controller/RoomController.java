package com.eic.springchatapp.api.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.eic.springchatapp.api.model.GenericRequest;
import com.eic.springchatapp.api.model.GenericResponse;
import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;
import com.eic.springchatapp.service.RoomService;

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

	@Autowired
	RoomService roomService;

	// Adding a new room and adding user in it

	@PostMapping("/addRoom")
	ResponseEntity<?> addRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(1).getDefaultMessage());

		} else {

			GenericResponse Response = roomService.createRoom(genericRequest.getRoomname(),
					genericRequest.getPassword());
			if (Response.getStatusCode() == HttpStatus.CREATED) {
				return joinRoom(genericRequest, bindingResult);
			} else {
				return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

			}

		}

	}

	// Adding the user to room
	@PostMapping("/joinRoom")
	ResponseEntity<?> joinRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

		} else {

			GenericResponse Response = roomService.addUserToRoon(genericRequest.getUsername(),
					genericRequest.getRoomname(), genericRequest.getPassword());

			if (Response.getStatusCode() == HttpStatus.CREATED) {
				return ResponseEntity.status(Response.getStatusCode()).body(Response.getRoom());
			} else {
				return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

			}
		}

	}

	// Adding the user to room
	@PostMapping("/getUsersInRoom")
	ResponseEntity<?> getUsersInRoom(@RequestBody GenericRequest genericRequest) {

		Room room = roomRepository.findByNameAndPassword(genericRequest.getRoomname(), genericRequest.getPassword());

		if (room != null) {

			return ResponseEntity.ok().body(room.getUsers());
		} else {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
		}

	}

}
