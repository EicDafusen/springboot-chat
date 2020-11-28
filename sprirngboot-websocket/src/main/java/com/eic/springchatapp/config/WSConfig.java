package com.eic.springchatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.eic.springchatapp.interceptor.AuthChannelInterceptor;
import com.eic.springchatapp.security.UserAuthenticationProvider;




@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	UserAuthenticationProvider userAuthenticationProvider;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		
		registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:3000").withSockJS();
			
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//for getting messages
		registry.enableSimpleBroker("/topic");
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
	    registration.setInterceptors(new AuthChannelInterceptor(userAuthenticationProvider));
	}


}
