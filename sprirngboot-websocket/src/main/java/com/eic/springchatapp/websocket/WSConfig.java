package com.eic.springchatapp.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.eic.springchatapp.security.UserAuthenticationProvider;
import com.eic.springchatapp.service.RoomService;




@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	UserAuthenticationProvider userAuthenticationProvider;
	@Autowired
	RoomService roomService;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		

		registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:3000").withSockJS();
	

		registry.setErrorHandler( new WSErrorHandler());
	}
	
	

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//for getting messages
		registry.enableSimpleBroker("/topic","/user");

	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
	    registration.setInterceptors(new AuthChannelInterceptor(userAuthenticationProvider,roomService));
	    
	}


}
