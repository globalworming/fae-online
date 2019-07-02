package com.headissue.fate.service;

import com.headissue.fate.model.Campaign;
import net.thucydides.core.annotations.Pending;
import org.junit.Before;
import org.junit.Test;
import screenplay.IntegrationTestBase;
import screenplay.actions.Add;
import screenplay.actions.CreateCharacter;
import screenplay.actions.CreateWorld;
import screenplay.actions.UpdateWorldDescription;
import screenplay.questions.WorldInformation;


import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static org.assertj.core.api.Assertions.assertThat;


public class ServiceTest extends IntegrationTestBase {
  
  private String worldName;

  @Override
  @Before
  public void setUp() {
    super.setUp();
    worldName = randomName();
  }

  @Test
  public void createWorld() {
    gameMaster.attemptsTo(CreateWorld.withName(worldName));
    then(gameMaster).should(seeThat("worldName is equal",
        WorldInformation.forWorld(worldName),
        t -> t.getName().equals(worldName)
    ));
  }

  @Test
  public void updateWorldDescription() {

    gameMaster.wasAbleTo(CreateWorld.withName(worldName));
    then(gameMaster).should(seeThat("world has no description",
        WorldInformation.forWorld(worldName),
        t -> t.getDescription() == null
    ));

    gameMaster.attemptsTo(UpdateWorldDescription.of(worldName).to("describe " + worldName));
    then(gameMaster).should(seeThat("description updated",
        WorldInformation.forWorld(worldName),
        t -> t.getDescription().equals("describe " + worldName)
    ));
  }

  @Test
  public void whenAddingCampaign() {

    gameMaster.wasAbleTo(CreateWorld.withName(worldName));
    gameMaster.attemptsTo(Add.content(new Campaign()).to(worldName));

    then(gameMaster).should(seeThat("world has campaign",
        WorldInformation.forWorld(worldName),
        t -> t.getContent() != null && !t.getContent().isEmpty()
    ));
  }

  @Test
  public void whenCreatingActor() {
    gameMaster.wasAbleTo(CreateWorld
        .withName(worldName)
        .withCampaign(new Campaign()));

    gameMaster.attemptsTo(CreateCharacter.forWorld(worldName));

    then(gameMaster).should(seeThat("world has a actor",
        WorldInformation.forWorld(worldName),
        t -> t.getActors() != null && !t.getContent().isEmpty()
    ));
  }

  @Test
  @Pending
  public void updateCharacter() {

  }

/*

  @Test
  public void updateCharacter() {
    Actor actor = createCharacter();
    World world = getWorldInfo(worldName);
    addCharacterTo(world, actor);
    actor.setName(worldName);
    actor.setStress(3);
    actor.setDilemma(new Aspect());
    actor.setHighConcept(new Aspect());
    actor.setEdge(2);
    actor.setRefresh(1);
    actor.setStunts(worldName + "stunts");
    updateCharacter(actor);

    List<Actor> actors = asJava(getCharacters(world));
    Actor storedActor = actors.get(0);
    assertThat(storedActor.getName()).isEqualTo(worldName);
    assertThat(storedActor.getStress()).isEqualTo(3);
    assertThat(storedActor.getEdge()).isEqualTo(2);
    assertThat(storedActor.getRefresh()).isEqualTo(1);
    assertThat(storedActor.getDilemma()).isNotNull();
    assertThat(storedActor.getHighConcept()).isNotNull();
  }

  private List asJava(Buffer<?> buffer) {
    return BufferHasAsJava(buffer).asJava();
  }

  @Test
  public void getCharacters() {
    World world = getWorldInfo(worldName);
    addCharacterTo(world, createCharacter());

    List<Actor> actors = asJava(getCharacters(world));
    assertThat(actors).size().isEqualTo(1);
  }

  @Test
  public void addAspectToWorld() {
    World enteredWorld = getWorldInfo(worldName);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);


    Aspect aspect = new Aspect();
    aspect.setName(worldName);
    addAspectTo(enteredWorld, aspect);

    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(1);
    Aspect storedAspect = (Aspect) asJava(getAspects(enteredWorld)).get(0);
    assertThat(storedAspect.getName()).isEqualTo(worldName);
  }


  @Override
  public Buffer<Aspect> getAspects(HasAspects hasAspects) {
    return fateService.getAspects(hasAspects);
  }

  @Test
  public void removeAspect() {
    World enteredWorld = getWorldInfo(worldName);

    Aspect aspect = new Aspect();
    addAspectTo(enteredWorld, aspect);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(1);

    removeAspectFrom(enteredWorld, aspect);
    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);
  }


  @Test
  public void updateAspect() {
    Aspect aspect = new Aspect();
    aspect.setName(worldName);
    World world = getWorldInfo(worldName);
    addAspectTo(world, aspect);

    Aspect storedAspect = (Aspect) asJava(getAspects(world)).get(0);
    assertThat(storedAspect.getName()).isEqualTo(worldName);

    storedAspect.setName("updated " + worldName);
    updateAspect(storedAspect);

    Aspect storedUpdatedAspect = (Aspect) asJava(getAspects(world)).get(0);
    assertThat(storedUpdatedAspect.getName()).isEqualTo("updated " + worldName);

  }

  @Override
  public Aspect updateAspect(Aspect aspect) {
    return fateService.updateAspect(aspect);
  }

  @Test
  public void changeAspectToCharacter() {
    
    World enteredWorld = getWorldInfo(worldName);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(0);

    Aspect aspect = addAspectTo(enteredWorld, new Aspect());

    changeAspectToCharacter(aspect, enteredWorld, enteredWorld);
    assertThat(asJava(getCharacters(enteredWorld))).size().isEqualTo(1);

    assertThat(asJava(getAspects(enteredWorld))).size().isEqualTo(0);
  }

  @Test
  public void createMookTest() {
    Actor actor = createCharacter();
    assertThat(asJava(getMooks(actor))).size().isEqualTo(0);
    createMook(actor.getId());
    assertThat(asJava(getMooks(actor))).size().isEqualTo(1);
  }

  @Test
  public void updateMookTest() {
    Actor actor = createCharacter();
    Mook mook = createMook(actor.getId());
    mook.setName("mook " + worldName);
    updateMook(mook);

    List<Mook> mooks = asJava(getMooks(actor));
    assertThat(mooks).size().isEqualTo(1);
    assertThat(mooks.get(0).getName()).isEqualTo("mook " + worldName);
  }

  @Override
  public Mook updateMook(Mook mook) {
    return fateService.updateMook(mook);
  }

  @Test
  public void getMooks() {
    World world = getWorldInfo(worldName);
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
*/
}