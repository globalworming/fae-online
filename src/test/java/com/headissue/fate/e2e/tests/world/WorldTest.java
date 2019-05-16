package com.headissue.fate.e2e.tests.world;

import com.headissue.fate.e2e.TestBase;
import com.headissue.fate.e2e.screenplay.actions.EditButCancelWorldDescription;
import com.headissue.fate.e2e.screenplay.actions.EntersWorld;
import com.headissue.fate.e2e.screenplay.actions.UpdatesWorldDescription;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.headissue.fate.model.World.NAME_OF_TESTWORLD;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;

@RunWith(SerenityRunner.class)
public class WorldTest extends TestBase {

  @Test
  public void enterWorld() {
    String randomWorld = randomWorld().getName();
    gm.attemptsTo(EntersWorld.withName(randomWorld));

    then(gm).should(eventually(seeThat(
        WebElementQuestion.the(".App h1"),
        WebElementStateMatchers.containsText("welcome to " + randomWorld)
    )));

    pc.attemptsTo(EntersWorld.withName(NAME_OF_TESTWORLD));

    then(pc).should(eventually(seeThat(
        WebElementQuestion.the(".App h1"),
        WebElementStateMatchers.containsText("welcome to " + NAME_OF_TESTWORLD)
    )));
  }

  @Test
  public void testWorldDescription() {
    givenThat(gm).wasAbleTo(EntersWorld.withName(NAME_OF_TESTWORLD));
    then(gm).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of world 0")
    ));
  }

  @Test
  public void testEditDescription() {
    String randomWorld = randomWorld().getName();
    givenThat(gm).wasAbleTo(EntersWorld.withName(randomWorld));

    when(gm).attemptsTo(new UpdatesWorldDescription("description of " + randomWorld));
    then(gm).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of " + randomWorld)
    ));

    when(pc).attemptsTo(EntersWorld.withName(randomWorld));
    then(pc).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of " + randomWorld)
    ));

    when(gm).attemptsTo(new UpdatesWorldDescription("updated description of " + randomWorld));
    then(gm).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("updated description of " + randomWorld)
    ));
    then(pc).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("updated description of " + randomWorld)
    ));
  }

  @Test
  public void testRemoveDescription() {
    String randomWorld = randomWorld().getName();
    givenThat(gm).wasAbleTo(EntersWorld.withName(randomWorld));
    when(pc).attemptsTo(EntersWorld.withName(randomWorld));

    when(gm).attemptsTo(new UpdatesWorldDescription("description of " + randomWorld));
    then(pc).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of " + randomWorld)
    ));

    when(gm).attemptsTo(new UpdatesWorldDescription(""));
    then(gm).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        IsNot.not(WebElementStateMatchers.containsText(randomWorld))
    ));
    then(pc).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        IsNot.not(WebElementStateMatchers.containsText(randomWorld))
    ));
  }

  @Test
  public void testCancelEditDescription() {
    String randomWorld = randomWorld().getName();
    givenThat(gm).wasAbleTo(EntersWorld.withName(randomWorld));
    givenThat(pc).wasAbleTo(EntersWorld.withName(randomWorld));

    when(gm).attemptsTo(new EditButCancelWorldDescription("new description of " + randomWorld));

    then(gm).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of " + randomWorld)
    ));
    then(pc).should(seeThat(
        WebElementQuestion.the(".e2e-world-description"),
        WebElementStateMatchers.containsText("description of " + randomWorld)
    ));
  }

}
