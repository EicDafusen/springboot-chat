package com.eic.springchatapp.api.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class WebSocketEventListener {
	
	@EventListener
	public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
		
		System.out.println(event.getUser());
		

	     
	}
}
