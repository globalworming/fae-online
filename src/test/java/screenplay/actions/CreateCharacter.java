package screenplay.actions;

import com.headissue.fate.model.Actor;
import com.headissue.fate.model.World;
import com.headissue.fate.service.FateService;
import net.serenitybdd.screenplay.Performable;
import screenplay.abilities.UseTheService;
import screenplay.questions.WorldInformation;

public class CreateCharacter implements Performable {

  private final String worldName;

  public CreateCharacter(String worldName) {
    this.worldName = worldName;
  }

  public static CreateCharacter forWorld(String worldName) {
    return new CreateCharacter(worldName);
  }

  @Override
  public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
    FateService service = UseTheService.as(actor).getService();
    World world = WorldInformation.forWorld(worldName).answeredBy(actor);
    service.addRoleTo(world, new Actor());
  }
}
