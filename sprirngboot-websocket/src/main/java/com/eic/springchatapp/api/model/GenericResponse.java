package com.eic.springchatapp.api.model;

import org.springframework.http.HttpStatus;

public class GenericResponse<T> {

	private HttpStatus statusCode;
	private String message;
	private T data;

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

	public  T getData() {
		return data;
	}

	public void setData( T data) {
		this.data = data;
	}

	public GenericResponse(HttpStatus statusCode, String message, T data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

}
