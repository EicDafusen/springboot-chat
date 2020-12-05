package com.eic.springchatapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;
import com.eic.springchatapp.api.repository.UserRepository;
import com.eic.springchatapp.model.User;

@SpringBootApplication
public class SpringBootWebSocketApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebSocketApp.class, args);
	}

	@Autowired
	RoomRepository roomRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {

		roomRepo.deleteAll();
		userRepo.deleteAll();

		roomRepo.insert(new Room("room1", "123"));
		roomRepo.insert(new Room("room2", "456"));
		
		
		userRepo.insert(new User("myname","mypassword"));
		userRepo.insert(new User("111","222"));
		userRepo.insert(new User("11","22"));


		


	}

}
