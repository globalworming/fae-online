package com.headissue.fate.service;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.IsContent;
import com.headissue.fate.model.World;
import com.headissue.fate.service.api.FateService;
import org.junit.Test;
import screenplay.IntegrationTestBase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTest extends IntegrationTestBase implements FateService {

  @Test
  public void enterWorld() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    assertThat(enteredWorld.getId()).isNotNull();
    assertThat(enteredWorld.getName()).isEqualTo(name);
  }

  @Override
  public World enterWorld(String worldName) {
    return fateService.enterWorld(worldName);
  }

  @Test
  public void updateWorldDescription() {
    String name = randomName();
    World updatedWorld = updateWorldDescription(enterWorld(name), "describe " + name);
    assertThat(updatedWorld.getDescription()).isEqualTo("describe " + name);
    assertThat(enterWorld(name).getDescription()).isEqualTo("describe " + name);
  }

  @Override
  public World updateWorldDescription(World world, String newDescription) {
    return fateService.updateWorldDescription(world, newDescription);
  }

  @Test
  public void addCampaign() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    Campaign campaign = new Campaign();
    campaign.setName(name);
    campaign.setContainer(enteredWorld);
    campaign = (Campaign) addContent(campaign);
    assertThat(campaign).isNotNull();
    assertThat(campaign.getName()).isEqualTo(name);
    assertThat(campaign.getContainer().getId()).isEqualTo(enteredWorld.getId());
  }

  @Override
  public IsContent addContent(IsContent content) {
    return fateService.addContent(content);
  }

  private String randomName() {
    return UUID.randomUUID().toString();
  }

}