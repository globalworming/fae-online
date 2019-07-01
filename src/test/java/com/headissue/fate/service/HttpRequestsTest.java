package com.headissue.fate.service;

import screenplay.IntegrationTestBase;
import screenplay.abilities.SendHttpRequests;
import screenplay.actions.EntersWorld;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static screenplay.E2ETestBase.randomWorld;


public class HttpRequestsTest extends IntegrationTestBase {


  Question<Boolean> heKnowsAWorld = new Question<Boolean>() {
    @Override
    public Boolean answeredBy(Actor actor) {
      return actor.recall("WORLD") != null;
    }

    @Override
    public String getSubject() {
      return "he knows of a world";
    }
  };

  @Test
  public void enterWorld() {
    String randomName = randomWorld().getName();
    when(newUser).attemptsTo(EntersWorld.withName(randomName));
    then(newUser).should(seeThat(heKnowsAWorld));
  }
}
