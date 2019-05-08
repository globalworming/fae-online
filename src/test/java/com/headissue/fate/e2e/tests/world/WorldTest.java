package com.headissue.fate.e2e.tests.world;

import com.headissue.fate.e2e.TestBase;
import com.headissue.fate.e2e.screenplay.actions.EntersWorld;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.UUID;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;

@RunWith(SerenityRunner.class)
public class WorldTest extends TestBase {

  @Test
  public void enterWorld() {
    String world1 = "world" + UUID.randomUUID().toString();
    int i = new Random().nextInt();
    gm.attemptsTo(EntersWorld.of(world1));

    then(gm).should(eventually(seeThat(
        WebElementQuestion.the(".App h1"),
        WebElementStateMatchers.containsText("welcome to " +world1)
    )));

    pc.attemptsTo(EntersWorld.of("world0"));

    then(gm).should(eventually(seeThat(
        WebElementQuestion.the(".App h1"),
        WebElementStateMatchers.containsText("welcome to " +world1)
    )));

  }
}
