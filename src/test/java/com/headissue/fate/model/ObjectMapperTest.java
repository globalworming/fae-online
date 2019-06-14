package com.headissue.fate.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class ObjectMapperTest {

  @Test
  public void mapWorld() throws Exception {
    World world = new World();
    String s = new ObjectMapper().writeValueAsString(world);
    assertThat(s, not(StringContains.containsString("created_at")));
  }

  @Test
  public void mapMessage() throws Exception {
    Message message = new Message();
    String s = new ObjectMapper().writeValueAsString(message);
    assertThat(s, not(StringContains.containsString("world")));
  }
}
