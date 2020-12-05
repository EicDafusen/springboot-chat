package com.eic.springchatapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.eic.springchatapp.model.MessageTemplate;

import com.eic.springchatapp.service.RoomService;

@Controller
@CrossOrigin
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemp;

	@Autowired
	RoomService roomService;

	@MessageMapping("/chat/{roomID}")
	// @SendTo("/topic")
	public void chatEndPoint(@DestinationVariable String roomID, @Payload MessageTemplate messageTemplate,
			Principal principal) {

		String username = principal.getName();

		if (username == null) {

			messagingTemp.convertAndSend("/user/" + username, new MessageTemplate("ERROR", "PLEASE LOG IN"));
		} else if (!roomService.isUserInRoom(roomID, username)) {
			messagingTemp.convertAndSend("/user/" + username,
					new MessageTemplate("ERROR", "YOU ARE NOT ALLOWED TO SEND MESSAGE IN THIS ROOM"));
		} else {
			messagingTemp.convertAndSend("/topic/" + roomID, messageTemplate);
		}

	}

	@MessageExceptionHandler
	public void handleException(RuntimeException message) {

	}

}
