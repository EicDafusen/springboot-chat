package com.eic.springchatapp.model;



public class MessageTemplate {
	
	private String sender;
	private String content;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", content=" + content + "]";
	}
	public MessageTemplate(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
	}
	
	
	

}

