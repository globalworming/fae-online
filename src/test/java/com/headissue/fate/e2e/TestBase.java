package com.headissue.fate.e2e;

import com.headissue.fate.e2e.screenplay.questions.SevereConsoleLogs;
import com.headissue.fate.model.World;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.Managed;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static org.junit.Assert.fail;

@ActiveProfiles("it")
public abstract class TestBase {

  @Rule
  public ThereShouldBeNoSevereJavascriptErrors rule = new ThereShouldBeNoSevereJavascriptErrors();

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

  public static World randomWorld() {
    World world = new World();
    world.setName("world" + UUID.randomUUID().toString());
    return world;
  }

  
  private class ThereShouldBeNoSevereJavascriptErrors implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
      return new Statement() {
        @Override
        public void evaluate() throws Throwable {
          base.evaluate();
          after();
        }

        private void after() {
          List<LogEntry> gmLogEntries = SevereConsoleLogs.of(gm);
          List<LogEntry> pcLogEntries = SevereConsoleLogs.of(pc);

          then(gm).should(seeThat(
              "there should be no error logs in the console",
              actor -> gmLogEntries,
              List::isEmpty
          ).orComplainWith(AssertionError.class, "severe logs:\n" + gmLogEntries.stream()
              .map(entry -> entry.toString() + "\n")
              .collect(Collectors.joining())
          ));
          then(pc).should(seeThat(
              "there should be no error logs in the console",
              actor -> pcLogEntries,
              List::isEmpty
          ).orComplainWith(AssertionError.class, "severe logs:\n" + gmLogEntries.stream()
              .map(entry -> entry.toString() + "\n")
              .collect(Collectors.joining())
          ));
          if (gmLogEntries.size() + pcLogEntries.size() > 0) {
            fail("there should be no error logs in the console");
          }
        }
      };
    }
  }
}
