package screenplay.questions;

import com.headissue.fate.model.World;
import com.headissue.fate.service.FateService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import screenplay.abilities.UseTheService;

public class WorldInformation extends QuestionWithDefaultSubject<World> implements Question<World> {

  private final String forName;

  public WorldInformation(String forName) {
    this.forName = forName;
  }

  public static WorldInformation forWorld(String forName) {
    return new WorldInformation(forName);
  }

  @Override
  public World answeredBy(Actor actor) {
    FateService service = UseTheService.as(actor).getService();
    return service.getWorldInfo(forName);
  }
}
