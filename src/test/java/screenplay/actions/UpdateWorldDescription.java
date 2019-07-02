package screenplay.actions;

import com.headissue.fate.model.World;
import com.headissue.fate.service.FateService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import screenplay.abilities.UseTheService;

public class UpdateWorldDescription implements Performable {

  private final String name;
  private final String newDescription;

  public UpdateWorldDescription(String name) {
    this(name, null);
  }

  public UpdateWorldDescription(String name, String newDescription) {
    this.name = name;
    this.newDescription = newDescription;
  }

  public static UpdateWorldDescription of(String name) {
    return new UpdateWorldDescription(name);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    FateService service = UseTheService.as(actor).getService();
    World worldInfo = service.getWorldInfo(name);
    service.updateWorldDescription(worldInfo, newDescription);
  }

  public UpdateWorldDescription to(String newDescription) {
    return new UpdateWorldDescription(this.name, newDescription);
  }
}
