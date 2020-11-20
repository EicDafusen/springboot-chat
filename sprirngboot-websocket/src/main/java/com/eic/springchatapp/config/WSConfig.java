package com.eic.springchatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

	

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		//for sending messages
		//Setting an end point 
		//With making cross orijin all " (*) "  you can call this end point anywhere
		registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:3000").withSockJS();
			
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//for getting messages
		registry.enableSimpleBroker("/topic");
		
	}
	


}
