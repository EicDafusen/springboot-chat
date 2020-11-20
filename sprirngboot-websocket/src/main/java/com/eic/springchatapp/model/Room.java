package com.eic.springchatapp.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "rooms")
public class Room {
	
	
	
	
	@Id
	private String id;
	
	private String name;
	private String password;
	private List<String> users = new ArrayList<String>();
	
	public Room(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", password=" + password + ", users=" + users + "]";
	}


	
	
	
	
	
	

}
