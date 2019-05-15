package com.headissue.fate.controller;

import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagesControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private MessagesController messagesController;

  @Autowired
  private WorldRepository worldRepository;


  @Test
  public void getWorld0Messages() throws Exception {
    assertThat(this.messagesController.getMessages(0).get(0).getContent(), Is.is("content0"));
  }
}