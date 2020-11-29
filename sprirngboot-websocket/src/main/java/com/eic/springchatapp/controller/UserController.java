package com.eic.springchatapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eic.springchatapp.api.repository.UserRepository;
import com.eic.springchatapp.model.User;

@RestController
@RequestMapping("user")

public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/login")
	public void loginUser(HttpServletRequest hreq) {

		try {

			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("name",
					"password");
			token.setDetails(new WebAuthenticationDetails(hreq));

			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (Exception e) {
			System.out.println(e);
			return;

		}

		return;
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
