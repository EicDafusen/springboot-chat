package com.eic.springchatapp.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.eic.springchatapp.security.UserAuthenticationProvider;



@Component
public class AuthChannelInterceptor implements ChannelInterceptor {
    
	
	
	UserAuthenticationProvider userAProvider;
	 public AuthChannelInterceptor(UserAuthenticationProvider userAProvider) {
		 
		 this.userAProvider = userAProvider;
	}
	
	

	@Override
	public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand() || StompCommand.SEND == accessor.getCommand()) {
             String username = accessor.getFirstNativeHeader("username");
             String password = accessor.getFirstNativeHeader("password");

             UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(username,password);
             
             
 			
             Authentication auth=  userAProvider.authenticate(token);
           
             SecurityContextHolder.getContext().setAuthentication(auth);
 			 
            accessor.setUser(auth);
        
            
            
        }
        return message;
    }
	
}

