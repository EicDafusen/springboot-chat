package com.eic.springchatapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eic.springchatapp.api.model.GenericRequest;
import com.eic.springchatapp.api.repository.UserRepository;
import com.eic.springchatapp.model.User;
import com.eic.springchatapp.security.UserAuthenticationProvider;

@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAuthenticationProvider userAuth;

	@PostMapping("/log")
	public ResponseEntity<?> loginUser(HttpServletRequest hreq) {

	

		return ResponseEntity.ok("");
	}
	
	
	

	
	@GetMapping("/signIn")
	public  ResponseEntity<?> signInUser(@RequestBody @Valid GenericRequest genericRequest, BindingResult bindingResult) {


		if (bindingResult.hasErrors()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {

			User newUser = new User(  genericRequest.getName() , genericRequest.getPassword());
			
			userRepository.insert(newUser);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
	}

}
