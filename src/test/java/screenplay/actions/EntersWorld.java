package screenplay.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import screenplay.abilities.SendHttpRequests;
import screenplay.page.HomePage;
import com.headissue.fate.model.World;
import net.serenitybdd.core.exceptions.TestCompromisedException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.Keys;

import java.io.IOException;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EntersWorld implements Performable {

  private final String name;

  public EntersWorld(String name) {
    this.name = name;
  }

  public static Performable withName(String name) {
    return instrumented(EntersWorld.class, name);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    if (actor.usingAbilityTo(BrowseTheWeb.class) != null) {
      performWithBrowser(actor);
      return;
    }

    if (actor.usingAbilityTo(SendHttpRequests.class) != null) {
      performWithHttpRequests(actor);
      return;
    }

    throw new TestCompromisedException("actor has no ability to perform");
  }

  private <T extends Actor> void performWithBrowser(T actor) {
    actor.attemptsTo(Open.browserOn(new HomePage()));
    actor.attemptsTo(Enter.theValue(name).into(".e2e-world-input").thenHit(Keys.ENTER));
  }

  private <T extends Actor> void performWithHttpRequests(T actor) {
    OkHttpClient client = new OkHttpClient();
    Request request =
        new Request.Builder().url("http://localhost:" + actor.recall("PORT") + "/service/enterWorld/" + name).build();

    World world = null;
    ObjectMapper mapper = new ObjectMapper();
    try (Response response = client.newCall(request).execute()) {
      world = mapper.readValue(response.body().string(), World.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    actor.remember("WORLD", world);
  }
}
