package com.headissue.fate.controller;

import com.headissue.fate.model.Message;
import com.headissue.fate.model.World;
import com.headissue.fate.repository.MessagesRepository;
import com.headissue.fate.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessagesController {

  @Autowired
  private MessagesRepository messagesRepository;

  @GetMapping("/message/{worldId}")
  public List<Message> getMessages(@PathVariable long worldId) {
    return messagesRepository.findByWorld(worldId);
  }
}