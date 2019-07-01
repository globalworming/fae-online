package com.headissue.fate.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ObjectMapperTest {

  @Autowired
  WorldRepository worldRepository;

  @Test
  public void mapWorld() throws Exception {
    World world = new World();
    new ObjectMapper().writeValueAsString(world);
  }

  @Test
  public void mapWorld0() throws Exception {
    World one = worldRepository.getOne(0L);
    new ObjectMapper().writeValueAsString(one);
  }

  @Test
  public void mapMessage() throws Exception {
    Message message = new Message();
    String s = new ObjectMapper().writeValueAsString(message);
    assertThat(s, not(containsString("world")));
  }
}
