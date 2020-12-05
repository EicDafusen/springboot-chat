package com.eic.springchatapp.websocket;

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
import com.eic.springchatapp.service.RoomService;




@Component
public class AuthChannelInterceptor implements ChannelInterceptor {
    
	RoomService roomService;
	
	
	UserAuthenticationProvider userAProvider;
	 public AuthChannelInterceptor(UserAuthenticationProvider userAProvider ,RoomService roomService) {
		 
		 this.userAProvider = userAProvider;
		 this.roomService = roomService;
	}
	
	

	@Override
	public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT == accessor.getCommand() ) {
             String username = accessor.getFirstNativeHeader("username");
             String password = accessor.getFirstNativeHeader("password");

             UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(username,password);
             
             
      
             Authentication auth=  userAProvider.authenticate(token);
                 
             SecurityContextHolder.getContext().setAuthentication(auth);
             
             accessor.setUser(auth);;
        }
        
        
        if(StompCommand.DISCONNECT == accessor.getCommand()) {
        	
        	
        	
       
        	roomService.disconnectUser(accessor.getUser().getName());
        }
            
       
        return message;
    }
	
	
}

