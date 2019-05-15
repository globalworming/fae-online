package com.headissue.fate.controller;

import com.headissue.fate.model.World;
import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorldControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private WorldController worldController;

  @Autowired
  private WorldRepository worldRepository;


  @Test
  public void getWorldId() throws Exception {
    long actual = this.worldController.getWorldId(World.NAME_OF_TESTWORLD);
    long expected = 0;
    assertThat(actual, Is.is(expected));
  }

  @Test
  public void getTestWorldDescription() throws Exception {
    World world = this.worldController.getWorld(World.ID_OF_TESTWORLD);
    assertThat(world.getDescription(), Is.is("description of world 0"));
  }

  @Test
  public void getWorldCreatesWorld() throws Exception {
    UUID uuid = UUID.randomUUID();
    assertThat(this.worldRepository.findByName(uuid.toString()), IsNull.nullValue());
    this.worldController.getWorldId(uuid.toString());
    assertThat(this.worldRepository.findByName(uuid.toString()).getName(), Is.is(uuid.toString()));
  }
}