package com.headissue.fate.controller;

import com.headissue.fate.model.World;
import com.headissue.fate.repository.MessagesRepository;
import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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

  @Test
  public void getWorldId() throws Exception {
    long actual = this.messagesController.getWorldId(World.NAME_OF_TESTWORLD);
    long expected = 0;
    assertThat(actual, Is.is(expected));
  }

  @Test
  public void getWorldCreatesWorld() throws Exception {
    UUID uuid = UUID.randomUUID();
    assertThat(this.worldRepository.findByName(uuid.toString()), IsNull.nullValue());
    this.messagesController.getWorldId(uuid.toString());
    assertThat(this.worldRepository.findByName(uuid.toString()).getName(), Is.is(uuid.toString()));
  }
}