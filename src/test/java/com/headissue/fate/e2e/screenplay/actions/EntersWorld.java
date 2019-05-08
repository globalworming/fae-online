package com.headissue.fate.e2e.screenplay.actions;

import com.headissue.fate.e2e.screenplay.page.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EntersWorld implements Performable {

  private final String name;

  public EntersWorld(String name) {
    this.name = name;
  }

  public static Performable of(String name) {
    return instrumented(EntersWorld.class, name);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Open.browserOn(new HomePage()));
    actor.attemptsTo(Enter.theValue(name).into(".e2e-world-input").thenHit(Keys.ENTER));

  }
}
