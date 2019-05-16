package com.headissue.fate.controller;

import com.headissue.fate.model.ChatMessage;
import com.headissue.fate.model.EditWorldMessage;
import com.headissue.fate.model.Message;
import com.headissue.fate.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class WebSocketController {

  @Autowired
  private MessagesRepository messagesRepository;
  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/world/sendMessage")
  public void sendMessage(@Payload ChatMessage chatMessage) {
    messagesRepository.save(new Message(chatMessage));
    messagingTemplate.convertAndSend(format("/world/%s", chatMessage.getWorld()), chatMessage);
  }

  @MessageMapping("/world/{worldId}/addUser")
  public void addUser(@DestinationVariable String worldId, @Payload ChatMessage chatMessage,
                      SimpMessageHeaderAccessor headerAccessor) {
    String currentWorldId = (String) headerAccessor.getSessionAttributes().put("worldId", worldId);
    if (currentWorldId != null) {
      ChatMessage leaveMessage = new ChatMessage();
      leaveMessage.setType(ChatMessage.MessageType.LEAVE);
      leaveMessage.setSender(chatMessage.getSender());
      messagingTemplate.convertAndSend(format("/world/%s", currentWorldId), leaveMessage);
    }
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    String topic = format("/world/%s", worldId);
    messagingTemplate.convertAndSend(topic, chatMessage);
  }

  @MessageMapping("/world/{worldId}/sendMessage")
  public void sendMessage(@DestinationVariable long worldId, @Payload EditWorldMessage editWorldMessage) {
    messagingTemplate.convertAndSend(format("/world/%s", worldId), editWorldMessage);

  }
}