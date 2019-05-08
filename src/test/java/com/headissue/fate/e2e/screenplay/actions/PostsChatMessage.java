package com.headissue.fate.e2e.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostsChatMessage implements Performable {

  private final String content;

  public PostsChatMessage(String content) {
    this.content = content;
  }

  public static Performable withContent(String content) {
    return instrumented(PostsChatMessage.class, content);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Enter.theValue(content).into(".e2e-chat-message-input").thenHit(Keys.ENTER));
  }
}
