package com.headissue.fate.controller;

import com.headissue.fate.model.EditWorldMessage;
import com.headissue.fate.model.Message;
import com.headissue.fate.repository.MessagesRepository;
import com.headissue.fate.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Controller
@Transactional
public class WebSocketController {

  @Autowired
  private MessagesRepository messagesRepository;
  @Autowired
  private WorldRepository worldRepository;
  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/world/sendMessage/{worldId}")
  @SendTo("/world/{worldId}")
  public Message sendMessage(@DestinationVariable String worldId, @Payload Message message) {
    message.setWorld(worldRepository.getOne(Long.valueOf(worldId)));
    messagesRepository.save(message);
    return message;
  }

  @MessageMapping("/world/{worldId}/addUser")
  public void addUser(@DestinationVariable String worldId, @Payload Message chatMessage,
                      SimpMessageHeaderAccessor headerAccessor) {
    String currentWorldId = (String) headerAccessor.getSessionAttributes().put("worldId", worldId);
    if (currentWorldId != null) {
      Message leaveMessage = new Message();
      leaveMessage.setMessageType(Message.Type.LEAVE);
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