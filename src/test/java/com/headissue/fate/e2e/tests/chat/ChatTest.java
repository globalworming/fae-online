package com.headissue.fate.e2e.tests.chat;

import com.headissue.fate.e2e.TestBase;
import com.headissue.fate.e2e.screenplay.actions.PostsChatMessage;
import com.headissue.fate.e2e.screenplay.page.HomePage;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;

@RunWith(SerenityRunner.class)
public class ChatTest extends TestBase {

  @Test
  public void chatTest() {
    int i = new Random().nextInt();
    gm.attemptsTo(Open.browserOn(new HomePage()));
    pc.attemptsTo(Open.browserOn(new HomePage()));

    gm.attemptsTo(PostsChatMessage.withContent(i + ""));
    then(pc).should(eventually(seeThat(
        WebElementQuestion.the(".e2e-chat-messages"),
        WebElementStateMatchers.containsText(i + "")
    )));
  }
}
