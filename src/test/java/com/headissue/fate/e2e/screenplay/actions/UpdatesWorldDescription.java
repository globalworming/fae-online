package com.headissue.fate.e2e.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

public class UpdatesWorldDescription implements Performable{
  private final String text;

  public UpdatesWorldDescription(String s) {
    this.text = s;
  }

  public static UpdatesWorldDescription to(String s) {
    return new UpdatesWorldDescription(s);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(".e2e-world-description .e2e-edit"));
    actor.attemptsTo(Enter.theValue(text).into(".e2e-world-description .e2e-input").thenHit(Keys.ENTER));
  }
}
