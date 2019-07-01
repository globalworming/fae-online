package com.headissue.fate.service;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Character;
import com.headissue.fate.model.HasAspects;
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

  @Test
  public void updateCharacter() {
    String name = randomName();
    World enteredWorld = enterWorld(name);

    Character character = createCharacter();
    character.setName(name);
    addCharacterTo(enteredWorld, character);
    updateCharacter(character);

    List<Character> characters = asJava(getCharacters(enteredWorld));
    assertThat(characters.get(0).getName()).isEqualTo(name);
  }

  @Override
  public Character updateCharacter(Character character) {
    return fateService.updateCharacter(character);
  }

  private List asJava(Buffer<?> buffer) {
    return BufferHasAsJava(buffer).asJava();
  }

  @Override
  public Character addCharacterTo(HasCharacters hasCharacters, Character character) {
    return fateService.addCharacterTo(hasCharacters, character);
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

  @Override
  public Aspect createAspect() {
    return fateService.createAspect();
  }

  @Test
  public void addAspectToWorld() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);


    Aspect aspect = createAspect();
    aspect.setName(name);
    updateAspect(aspect);
    addAspectTo(enteredWorld, aspect);

    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(1);
    Aspect storedAspect = (Aspect) asJava(getAspects(enteredWorld)).get(0);
    assertThat(storedAspect.getName()).isEqualTo(name);
  }


  @Override
  public Buffer<Aspect> getAspects(HasAspects hasAspects) {
    return fateService.getAspects(hasAspects);
  }

  @Test
  public void removeAspect() {
    String name = randomName();
    World enteredWorld = enterWorld(name);

    Aspect aspect = new Aspect();
    addAspectTo(enteredWorld, aspect);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(1);

    removeAspectFrom(enteredWorld, aspect);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);
  }


  @Override
  public Aspect removeAspectFrom(HasAspects hasAspects, Aspect aspect) {
    return fateService.removeAspectFrom(hasAspects, aspect);
  }

  @Override
  public Aspect addAspectTo(HasAspects hasAspects, Aspect aspect) {
    return fateService.addAspectTo(hasAspects, aspect);
  }

  @Override
  public Aspect updateAspect(Aspect aspect) {
    return fateService.updateAspect(aspect);
  }

  @Test
  public void changeAspectToCharacter() {
    String name = randomName();
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Aspect aspect = createAspect();
    aspect.setName(name);
    updateAspect(aspect);
    addAspectTo(enteredWorld, aspect);

    changeAspectToCharacter(aspect, enteredWorld, enteredWorld);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(1);

    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);
  }

  @Override
  public Character changeAspectToCharacter(Aspect aspect, HasAspects hasAspects, HasCharacters hasCharacters) {
    return fateService.changeAspectToCharacter(aspect, hasAspects, hasCharacters);
  }

}