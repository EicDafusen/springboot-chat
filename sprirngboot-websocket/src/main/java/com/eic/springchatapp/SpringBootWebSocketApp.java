package com.eic.springchatapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;

import com.eic.springchatapp.api.model.Room;
import com.eic.springchatapp.api.repository.RoomRepository;

@SpringBootApplication
public class SpringBootWebSocketApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebSocketApp.class, args);
	}

	@Autowired
	RoomRepository repo;

	@Override
	public void run(String... args) throws Exception {

		repo.deleteAll();

		repo.insert(new Room("room1", "123"));
		repo.insert(new Room("room2", "456"));

	}

}
