package com.headissue.fate.repository;

import com.headissue.fate.model.Message;
import com.headissue.fate.model.Player;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.headissue.fate.model.Player.TEST_GAME_MASTER;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlayerRepositoryTest {

  @Autowired
  private PlayerRepository playerRepository;

  @Test
  public void testPLayerExists() {
    Player player = playerRepository.getOne(TEST_GAME_MASTER.getId());
    assertThat(player.getName(), Is.is("player 0"));
  }
}
