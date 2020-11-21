package com.eic.springchatapp.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GenericRequest {

	@Size(min = 2, max = 14, message = "User name must be 2-14 characters long.")
	@NotNull(message = "Name cannot be null.")
	private String username;

	@Size(min = 2, max = 35, message = "Surname must be 2-35 characters long.")
	@NotNull(message = "Room name cannot be null.")
	private String roomname;

	@Size(min = 3, max = 35, message = "Surname must be 3-35 characters long.")
	@NotNull(message = "Name cannot be null.")
	private String password;

	public GenericRequest(String username, String roomname, String password) {
		super();
		this.username = username;
		this.roomname = roomname;
		this.password = password;
	}

	public GenericRequest() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}

}
