package com.eic.springchatapp.api.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.eic.springchatapp.api.model.GenericRequest;
import com.eic.springchatapp.api.model.GenericResponse;
import com.eic.springchatapp.api.repository.RoomRepository;
import com.eic.springchatapp.service.RoomService;

@RestController
@RequestMapping("room")
public class RoomController {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomService roomService;

	@PostMapping("/create")
	ResponseEntity<?> addRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult,
			Principal principal) {

		if (bindingResult.hasErrors()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());

		} else {

			GenericResponse<?> Response = roomService.createRoom(principal.getName(), genericRequest.getName(),
					genericRequest.getPassword());

			return ResponseEntity.status(Response.getStatusCode()).body(Response.getData());

		}

	}

	@PostMapping("/join")
	ResponseEntity<?> joinRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult,
			Principal principal) {

		if (bindingResult.hasErrors()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {

			GenericResponse<?> Response = roomService.addUserToRoom(principal.getName(), genericRequest.getName(),
					genericRequest.getPassword());

			return ResponseEntity.status(Response.getStatusCode()).body(Response.getData());
		}

	}

	@PostMapping("/user/list")
	ResponseEntity<?> getUsersInRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {

			GenericResponse<?> Response = roomService.getUsesInRoom(genericRequest.getName());
			return ResponseEntity.status(Response.getStatusCode()).body(Response.getData());

		}

	}

	@DeleteMapping("/delete")
	ResponseEntity<?> deleteRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());

		} else {
			GenericResponse<?> Response = roomService.deleteRoom(genericRequest.getName(),
					genericRequest.getPassword());
			return ResponseEntity.status(Response.getStatusCode()).body(Response.getData());

		}
	}

	@DeleteMapping("/user/delete")
	ResponseEntity<?> deleteUserFromRoom(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult,
			Principal principal) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {
			GenericResponse<?> Response = roomService.deleteUserFromRoom(principal.getName(),
					genericRequest.getName(), genericRequest.getPassword());
			return ResponseEntity.status(Response.getStatusCode()).body(Response.getData());

		}
	}

}
