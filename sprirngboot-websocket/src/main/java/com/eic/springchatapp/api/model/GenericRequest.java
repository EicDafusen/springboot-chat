package com.eic.springchatapp.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GenericRequest {

	@Size(min = 2, max = 35, message = "Roomname must be 2-35 characters long.")
	@NotNull(message = "Room name cannot be null.")
	private String name;

	@Size(min = 3, max = 35, message = "Password must be 3-35 characters long.")
	@NotNull(message = "Name cannot be null.")
	private String password;

	public GenericRequest(String roomname, String password) {
		super();
		this.name = roomname;
		this.password = password;
	}

	public GenericRequest() {

	}

	public String getName() {
		return name;
	}

	public void setName(String roomname) {
		this.name = roomname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

}
