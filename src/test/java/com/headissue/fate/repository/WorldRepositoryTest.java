package com.headissue.fate.repository;

import e2e.world.WorldTest;
import com.headissue.fate.model.Player;
import com.headissue.fate.model.World;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorldRepositoryTest {

  @Autowired
  private WorldRepository worldRepository;

  @Test
  public void testWorldExists() {
    assertThat(worldRepository.findByName(WorldTest.NAME_OF_TESTWORLD).getId(), Is.is(0L));
  }

  @Test
  public void testWorldAttributes() {
    World world = worldRepository.findById(0L).orElseThrow(RuntimeException::new);
    World expectedWorld = new World();
    expectedWorld.setId(0L);
    expectedWorld.setDescription("description of world 0");
    expectedWorld.setName("world 0");
    expectedWorld.setGameMaster(Player.TEST_GAME_MASTER);

    assertThat(world, IsEqual.equalTo(expectedWorld));
  }
}