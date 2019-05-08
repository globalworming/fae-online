package com.headissue.fate.controller;

import com.headissue.fate.model.ChatMessage;
import com.headissue.fate.model.Message;
import com.headissue.fate.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class ChatController {

  @Autowired
  private MessagesRepository messagesRepository;
  @Autowired
  private SimpMessageSendingOperations messagingTemplate;


  @MessageMapping("/world/{roomId}/sendMessage")
  public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
    messagesRepository.save(new Message(chatMessage));
    messagingTemplate.convertAndSend(format("/world/%s", roomId), chatMessage);
  }

  @MessageMapping("/world/{worldId}/addUser")
  public void addUser(@DestinationVariable String roomId, @Payload ChatMessage chatMessage,
                      SimpMessageHeaderAccessor headerAccessor) {
    String currentWorldId = (String) headerAccessor.getSessionAttributes().put("worldId", roomId);
    if (currentWorldId != null) {
      ChatMessage leaveMessage = new ChatMessage();
      leaveMessage.setType(ChatMessage.MessageType.LEAVE);
      leaveMessage.setSender(chatMessage.getSender());
      messagingTemplate.convertAndSend(format("/world/%s", currentWorldId), leaveMessage);
    }
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    messagingTemplate.convertAndSend(format("/world/%s", roomId), chatMessage);
  }

}