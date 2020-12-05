package com.eic.springchatapp.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;


public class WSErrorHandler  extends StompSubProtocolErrorHandler{


	
	@Override
	protected Message<byte[]> handleInternal(StompHeaderAccessor errorHeaderAccessor, byte[] errorPayload,
			Throwable cause, StompHeaderAccessor clientHeaderAccessor) {
		
		
		errorHeaderAccessor.setMessage(cause.getCause().getLocalizedMessage());
		if( cause instanceof AuthenticationException) {
			errorHeaderAccessor.setMessage("Bad credentials error");

		}
		
		
		return MessageBuilder.createMessage(errorPayload, errorHeaderAccessor.getMessageHeaders());

	}
	
}
