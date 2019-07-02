package com.headissue.fate.service;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Character;
import com.headissue.fate.model.HasAspects;
import com.headissue.fate.model.HasCharacters;
import com.headissue.fate.model.IsContent;
import com.headissue.fate.model.Mook;
import com.headissue.fate.model.World;
import com.headissue.fate.service.api.FateService;
import org.junit.Before;
import org.junit.Test;
import scala.collection.mutable.Buffer;
import screenplay.IntegrationTestBase;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static scala.jdk.CollectionConverters.BufferHasAsJava;


public class ServiceTest extends IntegrationTestBase implements FateService {
  
  String name;

  @Override
  @Before
  public void setUp() {
    super.setUp();
    name = randomName();
  }

  @Test
  public void enterWorld() {
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
    World updatedWorld = updateWorldDescription(enterWorld(name), "describe " + name);
    assertThat(updatedWorld.getDescription()).isEqualTo("describe " + name);
    assertThat(enterWorld(name).getDescription()).isEqualTo("describe " + name);
  }

  @Override
  public World updateWorldDescription(World world, String newDescription) {
    return fateService.updateWorldDescription(world, newDescription);
  }

  @Test
  public void addCampaignToWorld() {
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
  public void createCharacterTest() {
    assertThat(createCharacter().getId()).isNotNull();
  }

  @Override
  public Character createCharacter() {
    return fateService.createCharacter();
  }

  @Test
  public void addCharacterToWorld() {
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Character character = createCharacter();
    addCharacterTo(enteredWorld, character);

    List<Character> characters = asJava(getCharacters(enteredWorld));
    assertThat(characters).containsOnly(character);
  }

  @Test
  public void updateCharacter() {
    Character character = createCharacter();
    addCharacterTo(enterWorld(name), character);
    character.setName(name);
    character.setStress(3);
    character.setDilemma(createAspect());
    character.setHighConcept(createAspect());
    character.setEdge(2);
    character.setRefresh(1);
    character.setStunts(name + "stunts");
    updateCharacter(character);

    List<Character> characters = asJava(getCharacters(enterWorld(name)));
    Character storedCharacter = characters.get(0);
    assertThat(storedCharacter.getName()).isEqualTo(name);
    assertThat(storedCharacter.getStress()).isEqualTo(3);
    assertThat(storedCharacter.getEdge()).isEqualTo(2);
    assertThat(storedCharacter.getRefresh()).isEqualTo(1);
    assertThat(storedCharacter.getDilemma()).isNotNull();
    assertThat(storedCharacter.getHighConcept()).isNotNull();
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
    World world = enterWorld(name);
    addCharacterTo(world, createCharacter());

    List<Character> characters = asJava(getCharacters(world));
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

  @Test
  public void updateAspect() {
    Aspect aspect = createAspect();
    aspect.setName(name);
    updateAspect(aspect);
    World world = enterWorld(name);
    addAspectTo(world, aspect);

    Aspect storedAspect = (Aspect) asJava(getAspects(world)).get(0);
    assertThat(storedAspect.getName()).isEqualTo(name);
  }

  @Override
  public Aspect updateAspect(Aspect aspect) {
    return fateService.updateAspect(aspect);
  }

  @Test
  public void changeAspectToCharacter() {
    
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Aspect aspect = createAspect();
    addAspectTo(enteredWorld, aspect);

    changeAspectToCharacter(aspect, enteredWorld, enteredWorld);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(1);

    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);
  }

  @Override
  public Character changeAspectToCharacter(Aspect aspect, HasAspects hasAspects, HasCharacters hasCharacters) {
    return fateService.changeAspectToCharacter(aspect, hasAspects, hasCharacters);
  }

  @Test
  public void createMookTest() {
    Character character = createCharacter();
    assertThat(asJava(getMooks(character))).size().isEqualTo(0);
    createMook(character.getId());
    assertThat(asJava(getMooks(character))).size().isEqualTo(1);
  }

  @Override
  public Mook createMook(long owningCharacterId) {
    return fateService.createMook(owningCharacterId);
  }

  @Test
  public void updateMookTest() {
    Character character = createCharacter();
    Mook mook = createMook(character.getId());
    mook.setName("mook " + name);
    updateMook(mook);

    List<Mook> mooks = asJava(getMooks(character));
    assertThat(mooks).size().isEqualTo(1);
    assertThat(mooks.get(0).getName()).isEqualTo("mook " + name);
  }

  @Override
  public Mook updateMook(Mook mook) {
    return fateService.updateMook(mook);
  }

  @Test
  public void getMooks() {
    World world = enterWorld(name);
    Character character = createCharacter();
    addCharacterTo(world, character);
    createMook(character.getId());

    List<Character> characters = asJava(getCharacters(world));
    assertThat(characters).size().isEqualTo(1);
    List<Mook> mooks = asJava(getMooks(characters.get(0)));
    assertThat(mooks).size().isEqualTo(1);
  }

  @Override
  public Buffer<Mook> getMooks(Character character) {
    return fateService.getMooks(character);
  }

}