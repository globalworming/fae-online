package com.headissue.fate.e2e.tests.world;

import com.headissue.fate.e2e.TestBase;
import com.headissue.fate.e2e.screenplay.actions.EntersWorld;
import com.headissue.fate.model.World;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.headissue.fate.model.World.NAME_OF_TESTWORLD;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;

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
        WebElementStateMatchers.containsOnlyText("description of world 0")
    ));
  }
}
