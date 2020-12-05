package com.eic.springchatapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void signInUser() {

		try {

			// Adding dummy user for now
			User user = new User("name", "password");

			userRepository.save(user);

		} catch (Exception e) {
			System.out.println(e);
			return;

		}

		return;
	}

}
