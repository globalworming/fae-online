package com.headissue.fate.service;

import com.headissue.fate.model.Aspect;
import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.Actor;
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


  // FIXME verscreenplay
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
  public Actor createCharacter() {
    return fateService.createCharacter();
  }

  @Test
  public void addCharacterToWorld() {
    World enteredWorld = enterWorld(name);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Actor actor = createCharacter();
    addCharacterTo(enteredWorld, actor);

    List<Actor> actors = asJava(getCharacters(enteredWorld));
    assertThat(actors).containsOnly(actor);
  }

  @Test
  public void updateCharacter() {
    Actor actor = createCharacter();
    addCharacterTo(enterWorld(name), actor);
    actor.setName(name);
    actor.setStress(3);
    actor.setDilemma(createAspect());
    actor.setHighConcept(createAspect());
    actor.setEdge(2);
    actor.setRefresh(1);
    actor.setStunts(name + "stunts");
    updateCharacter(actor);

    List<Actor> actors = asJava(getCharacters(enterWorld(name)));
    Actor storedActor = actors.get(0);
    assertThat(storedActor.getName()).isEqualTo(name);
    assertThat(storedActor.getStress()).isEqualTo(3);
    assertThat(storedActor.getEdge()).isEqualTo(2);
    assertThat(storedActor.getRefresh()).isEqualTo(1);
    assertThat(storedActor.getDilemma()).isNotNull();
    assertThat(storedActor.getHighConcept()).isNotNull();
  }

  @Override
  public Actor updateCharacter(Actor actor) {
    return fateService.updateCharacter(actor);
  }

  private List asJava(Buffer<?> buffer) {
    return BufferHasAsJava(buffer).asJava();
  }

  @Override
  public Actor addCharacterTo(HasCharacters hasCharacters, Actor actor) {
    return fateService.addCharacterTo(hasCharacters, actor);
  }

  @Test
  public void getCharacters() {
    World world = enterWorld(name);
    addCharacterTo(world, createCharacter());

    List<Actor> actors = asJava(getCharacters(world));
    assertThat(actors).size().isEqualTo(1);
  }

  @Override
  public Buffer<Actor> getCharacters(HasCharacters hasCharacters) {
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
  public Actor changeAspectToCharacter(Aspect aspect, HasAspects hasAspects, HasCharacters hasCharacters) {
    return fateService.changeAspectToCharacter(aspect, hasAspects, hasCharacters);
  }

  @Test
  public void createMookTest() {
    Actor actor = createCharacter();
    assertThat(asJava(getMooks(actor))).size().isEqualTo(0);
    createMook(actor.getId());
    assertThat(asJava(getMooks(actor))).size().isEqualTo(1);
  }

  @Override
  public Mook createMook(long owningCharacterId) {
    return fateService.createMook(owningCharacterId);
  }

  @Test
  public void updateMookTest() {
    Actor actor = createCharacter();
    Mook mook = createMook(actor.getId());
    mook.setName("mook " + name);
    updateMook(mook);

    List<Mook> mooks = asJava(getMooks(actor));
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
    Actor actor = createCharacter();
    addCharacterTo(world, actor);
    createMook(actor.getId());

    List<Actor> actors = asJava(getCharacters(world));
    assertThat(actors).size().isEqualTo(1);
    List<Mook> mooks = asJava(getMooks(actors.get(0)));
    assertThat(mooks).size().isEqualTo(1);
  }

  @Override
  public Buffer<Mook> getMooks(Actor actor) {
    return fateService.getMooks(actor);
  }

}