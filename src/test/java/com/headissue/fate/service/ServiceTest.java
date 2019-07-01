package com.headissue.fate.service;

import com.headissue.fate.model.World;
import com.headissue.fate.service.api.FateService;
import org.junit.Test;
import screenplay.IntegrationTestBase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTest extends IntegrationTestBase implements FateService {

  @Test
  public void serviceTest() {
    assertThat(fateService.getMessage()).isEqualTo("The service says: hello");
  }

  @Test
  public void enterWorld() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    assertThat(enteredWorld.getId()).isNotNull();
    assertThat(enteredWorld.getName()).isEqualTo(name);
  }

  public World enterWorld(String worldName) {
    return fateService.enterWorld(worldName);
  }

  private String randomName() {
    return UUID.randomUUID().toString();
  }

}