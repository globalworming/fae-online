package screenplay.abilities;

import com.headissue.fate.model.World;
import com.headissue.fate.service.FateService;
import net.serenitybdd.core.exceptions.TestCompromisedException;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.RefersToActor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.exceptions.ActorCannotBrowseTheWebException;

public class UseTheService implements Ability, RefersToActor {

  private final FateService fateService;
  private Actor actor;

  public UseTheService(FateService fateService) {
    this.fateService = fateService;
  }

  public static UseTheService as(Actor actor) {
    if (actor.abilityTo(UseTheService.class) == null) {
      throw new TestCompromisedException(actor.getName() + "misses the ability");
    }
    return actor.abilityTo(UseTheService.class).asActor(actor);
  }

  @Override
  public <T extends Ability> T asActor(Actor actor) {
    this.actor = actor;
    return (T) this;
  }

  public FateService getService() {
    return fateService;
  }
}
