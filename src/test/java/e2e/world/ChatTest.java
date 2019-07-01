package e2e.world;

import screenplay.E2ETestBase;
import screenplay.actions.EntersWorld;
import screenplay.actions.PostsChatMessage;
import screenplay.page.HomePage;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.EventualConsequence;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.core.IsNot;

import java.util.Random;
import java.util.UUID;

import static e2e.world.WorldTest.NAME_OF_TESTWORLD;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;

@RunWith(SerenityRunner.class)
public class ChatTest extends E2ETestBase {

  @Test
  public void postToSameWorld() {
    String world1 = randomWorld().getName();
    int i = new Random().nextInt();
    gm.attemptsTo(EntersWorld.withName(world1));
    pc.attemptsTo(EntersWorld.withName(world1));

    gm.attemptsTo(PostsChatMessage.withContent(i + ""));
    then(pc).should(seeThatChatContains(i + ""));
  }

  private EventualConsequence<WebElementState> seeThatChatContains(String message) {
    return eventually(seeThat(
        WebElementQuestion.the(HomePage.chat),
        WebElementStateMatchers.containsText(message)
    ));
  }

  @Test
  public void fetchDifferentWorlds() {
    String world1 = "world" + UUID.randomUUID().toString();
    int i = new Random().nextInt();
    gm.attemptsTo(EntersWorld.withName(world1));
    gm.attemptsTo(PostsChatMessage.withContent(i + ""));

    then(gm).should(seeThatChatContains(i + ""));

    pc.attemptsTo(EntersWorld.withName(NAME_OF_TESTWORLD));
    then(pc).should(eventually(seeThat(
        WebElementQuestion.the(HomePage.chat),
        IsNot.not(WebElementStateMatchers.containsText(i + ""))
    )));
  }

  @Test
  public void postInDifferentWorlds() {
    String world1 = randomWorld().getName();
    int i = new Random().nextInt();
    gm.attemptsTo(EntersWorld.withName(world1));
    pc.attemptsTo(EntersWorld.withName(NAME_OF_TESTWORLD));
    gm.attemptsTo(PostsChatMessage.withContent(i + ""));
    then(pc).should(eventually(seeThat(
        WebElementQuestion.the(HomePage.chat),
        IsNot.not(WebElementStateMatchers.containsText(i + ""))
    )));
  }
}
