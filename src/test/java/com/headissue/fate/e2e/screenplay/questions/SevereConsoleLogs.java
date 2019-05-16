package com.headissue.fate.e2e.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.logging.Level;

public class SevereConsoleLogs {

  public static List<LogEntry> of(Actor actor) {
    return BrowseTheWeb.as(actor).getDriver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
  }
}
