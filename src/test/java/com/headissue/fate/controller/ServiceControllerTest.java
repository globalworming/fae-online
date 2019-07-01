package com.headissue.fate.controller;

import org.junit.Test;
import screenplay.IntegrationTestBase;
import screenplay.actions.EntersWorld;
import screenplay.questions.KnowOfAWorld;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static screenplay.E2ETestBase.randomWorld;


public class ServiceControllerTest extends IntegrationTestBase {

  @Test
  public void enterWorld() {
    String randomName = randomWorld().getName();
    when(newUser).attemptsTo(EntersWorld.withName(randomName));
    then(newUser).should(seeThat(new KnowOfAWorld()));
  }

}
