package com.headissue.fate.e2e.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

public class EditButCancelWorldDescription implements Performable {


  private final String editedText;

  public EditButCancelWorldDescription(String editetText) {
  this.editedText = editetText;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(".e2e-world-description .e2e-edit"));
    actor.attemptsTo(Enter.theValue(editedText).into(".e2e-world-description .e2e-input"));
    actor.attemptsTo(Click.on(".e2e-world-description .e2e-cancel"));
  }
}
