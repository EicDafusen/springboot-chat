package com.eic.springchatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.eic.springchatapp.model.Message;



/*  	TODO
 * 
 * 	-Validation
 * 
 * 
 * */

@Controller
@CrossOrigin // ???
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemp;
	
	
	@MessageMapping("/chat/{roomID}")
	//@SendTo("/topic")
	public void chatEndPoint(@DestinationVariable String roomID , @Payload Message message) {
		
		System.out.println(message);		
		messagingTemp.convertAndSend("/topic/"+roomID, message);	
	}
	
	
	
	

}
