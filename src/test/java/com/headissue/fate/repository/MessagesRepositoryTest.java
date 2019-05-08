package com.headissue.fate.repository;

import com.headissue.fate.model.Message;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest

public class MessagesRepositoryTest {

  @Autowired
  private MessagesRepository messagesRepository;

  @Test
  public void world0Messages() {
    List<Message> byWorldidref = messagesRepository.findByWorld(0);
    assertThat(byWorldidref.size(), Is.is(1));
    assertThat(byWorldidref.get(0).getContent(), Is.is("content0"));
  }
}