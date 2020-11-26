package com.eic.springchatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.eic.springchatapp.model.MessageTemplate;

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
	// @SendTo("/topic")
	public void chatEndPoint(@DestinationVariable String roomID, @Payload MessageTemplate messageTemplate) {

		System.out.println(messageTemplate);
		messagingTemp.convertAndSend("/topic/" + roomID, messageTemplate);
	}
	
	
	@SubscribeMapping("/chat")
	public void userSubscribed(@DestinationVariable String roomID,@DestinationVariable String userName) {
		
	}
}
