package com.headissue.fate.controller;

import com.headissue.fate.model.World;
import com.headissue.fate.repository.WorldRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static e2e.world.WorldTest.ID_OF_TESTWORLD;
import static e2e.world.WorldTest.NAME_OF_TESTWORLD;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorldControllerTest {

  @Autowired
  private WorldController worldController;

  @Autowired
  private WorldRepository worldRepository;


  @Test
  public void getWorldId() throws Exception {
    long actual = this.worldController.getWorldByName(NAME_OF_TESTWORLD).getId();
    long expected = 0;
    assertThat(actual, Is.is(expected));
  }

  @Test
  public void getTestWorldDescription() throws Exception {
    World world = this.worldController.getWorld(ID_OF_TESTWORLD);
    assertThat(world.getDescription(), Is.is("description of world 0"));

  }

  @Test
  public void updateWorldDescription() {
    UUID uuid = UUID.randomUUID();
    long worldId = this.worldController.getWorldByName(uuid.toString()).getId();
    assertThat(this.worldRepository.findById(worldId).orElse(null).getDescription(),
        IsNull.nullValue());
    this.worldController.putWorldDescription(worldId, "description" + uuid.toString());
    assertThat(this.worldRepository.findById(worldId).orElse(null).getDescription(),
        Is.is("description" + uuid.toString()));
  }

  @Test
  public void getWorldCreatesWorld() throws Exception {
    UUID uuid = UUID.randomUUID();
    assertThat(this.worldRepository.findByName(uuid.toString()), IsNull.nullValue());
    this.worldController.getWorldByName(uuid.toString()).getId();
    assertThat(this.worldRepository.findByName(uuid.toString()).getName(), Is.is(uuid.toString()));
  }
}