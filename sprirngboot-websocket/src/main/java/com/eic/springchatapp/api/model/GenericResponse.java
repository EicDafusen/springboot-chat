package com.eic.springchatapp.api.model;

import org.springframework.http.HttpStatus;

public class GenericResponse {

	private HttpStatus statusCode;
	private String message;
	private Room room;

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public GenericResponse(HttpStatus statusCode, String message, Room room) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.room = room;
	}

}
