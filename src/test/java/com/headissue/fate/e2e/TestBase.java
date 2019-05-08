package com.headissue.fate.e2e;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;

public abstract class TestBase {

  @Managed(uniqueSession = true, clearCookies = ClearCookiesPolicy.BeforeEachTest)
  private WebDriver aBrowser;

  @Managed(uniqueSession = true, clearCookies = ClearCookiesPolicy.BeforeEachTest)
  private WebDriver aSecondBrowser;

  public Actor gm = new Actor("GM");
  public Actor pc = new Actor("PC");

  @Before
  public void setUp() {
    givenThat(gm).can(BrowseTheWeb.with(aBrowser));
    givenThat(pc).can(BrowseTheWeb.with(aSecondBrowser));
  }

}
