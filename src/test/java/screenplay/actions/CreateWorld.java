package screenplay.actions;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.service.FateService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import screenplay.abilities.UseTheService;

public class CreateWorld implements Performable {

  private final String worldName;
  private final Campaign campaign;

  public CreateWorld(String name) {
    this(name, null);
  }

  public CreateWorld(String worldName, Campaign campaign) {
    this.worldName = worldName;
    this.campaign = campaign;
  }

  public static CreateWorld withName(String worldName) {
    return new CreateWorld(worldName);
  }

  public CreateWorld withCampaign(Campaign campaign) {
    return new CreateWorld(worldName, campaign);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    FateService service = UseTheService.as(actor).getService();
    service.createWorld(worldName);

    if (campaign != null) {
      actor.attemptsTo(Add.content(campaign).to(worldName));
    }

  }

}
