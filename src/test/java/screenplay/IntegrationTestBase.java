package screenplay;


import com.headissue.fate.service.FateService;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;
import screenplay.abilities.SendHttpRequests;

import java.util.UUID;

@RunWith(SerenityRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public abstract class IntegrationTestBase {

  @Autowired
  protected FateService fateService;

  @Rule
  public SpringIntegrationMethodRule springIntegrationMethodRule = new SpringIntegrationMethodRule();

  private Cast cast = Cast.whereEveryoneCan(new SendHttpRequests());
  protected Actor newUser = cast.actorNamed("new user");

  @LocalServerPort
  private int port;

  @Before
  public void setUp() {
    newUser.remember("PORT", port);
  }

  protected final String randomName() {
    return UUID.randomUUID().toString();
  }

}