package com.headissue.fate.service;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Character;
import com.headissue.fate.model.HasCharacters;
import com.headissue.fate.model.IsContent;
import com.headissue.fate.model.World;
import com.headissue.fate.service.api.FateService;
import org.junit.Test;
import scala.collection.mutable.Buffer;
import screenplay.IntegrationTestBase;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static scala.jdk.CollectionConverters.BufferHasAsJava;


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

  @Test
  public void testCreateCharacter() {
    assertThat(createCharacter().getId()).isNotNull();
  }

  @Override
  public Character createCharacter() {
    return fateService.createCharacter();
  }

  @Test
  public void addCharacterToWorld() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Character character = createCharacter();
    addCharacterTo(enteredWorld, character);

    List<Character> characters = asJava(getCharacters(enteredWorld));
    assertThat(characters).containsOnly(character);
  }

  private List<Character> asJava(Buffer<Character> characters) {
    return (List<Character>) BufferHasAsJava(characters).asJava();
  }

  @Override
  public void addCharacterTo(HasCharacters hasCharacters, Character character) {
    fateService.addCharacterTo(hasCharacters, character);
  }

  @Test
  public void getCharacters() {
    List<Character> characters = asJava(getCharacters(enterWorld("world 0")));
    assertThat(characters).size().isEqualTo(1);
  }

  @Override
  public Buffer<Character> getCharacters(HasCharacters hasCharacters) {
    return fateService.getCharacters(hasCharacters);
  }

}