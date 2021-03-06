package com.eic.springchatapp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;

	
	private String name;

	
	private String password;
	
	private List<String> rooms = new ArrayList<String>();


	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public List<String> getRooms() {
		return rooms;
	}

	public void setRooms(List<String> users) {
		this.rooms = users;
	}

	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		

}
