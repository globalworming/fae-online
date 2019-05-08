package com.headissue.fate.repository;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest

public class WorldRepositoryTest {

  @Autowired
  private WorldRepository worldRepository;

  @Test
  public void world0Messages() {
    assertThat(worldRepository.findByName("world0").getId(), Is.is(0L));
  }
}