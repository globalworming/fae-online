package com.headissue.fate.repository;

import com.headissue.fate.model.Message;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessagesRepositoryTest {

  @Autowired
  private MessagesRepository messagesRepository;

  @Autowired
  WorldRepository worldRepository;

  @Test
  public void testWorldMessages() {
    List<Message> messages = messagesRepository.findByWorld(worldRepository.getOne(0L));
    assertThat(messages.size(), Is.is(1));
    assertThat(messages.get(0).getContent(), Is.is("content0"));
  }
}