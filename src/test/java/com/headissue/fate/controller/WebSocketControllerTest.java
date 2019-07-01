package com.headissue.fate.controller;

import screenplay.E2ETestBase;
import com.headissue.fate.model.Message;
import com.headissue.fate.model.World;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class WebSocketControllerTest {

  @Autowired
  private WebSocketController webSocketController ;

  @Autowired
  private WorldController worldController;

  @Autowired
  private MessagesController messagesController;

  @Test
  public void createMessageForWorld() {
    long id = worldController.getWorldByName(E2ETestBase.randomWorld().getName()).getId();
    World world = worldController.getWorld(id);
    Message chatMessage = new Message();
    chatMessage.setContent("content");
    chatMessage.setWorld(world);
    chatMessage.setMessageType(Message.Type.CHAT);
    chatMessage.setSender("sender");
    webSocketController.sendMessage(String.valueOf(world.getId()), chatMessage);
    Collection<Message> messages = messagesController.getMessages(id);
    assertThat(messages, Matchers.hasItem(chatMessage));
  }
}