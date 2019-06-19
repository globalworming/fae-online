package com.headissue.fate.controller;

import com.headissue.fate.model.Message;
import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class MessagesControllerTest {

  @Autowired
  private MessagesController messagesController;

  @Test
  public void getWorld0Messages() {
    Collection<Message> messages = this.messagesController.getMessages(0L);
    assertThat(messages, not(nullValue()));
    assertThat(messages.size(), is(1));
    assertThat(messages.stream().findFirst().get().getContent(), is("content0"));
  }
}