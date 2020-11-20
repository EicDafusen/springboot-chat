package com.eic.springchatapp.model;

public class MRequest {

	private String username;
	private String roomname;
	private String password;
	
	public MRequest(String username, String roomname, String password) {
		super();
		this.username = username;
		this.roomname = roomname;
		this.password = password;
	}
	
	public MRequest(){
		
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
