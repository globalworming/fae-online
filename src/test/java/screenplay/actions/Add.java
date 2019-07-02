package screenplay.actions;

import com.headissue.fate.model.Campaign;
import com.headissue.fate.model.IsContainer;
import com.headissue.fate.model.IsContent;
import com.headissue.fate.model.World;
import com.headissue.fate.service.FateService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import screenplay.abilities.UseTheService;

import java.util.Collections;

public class Add implements Performable {

  private final String worldName;
  private final Campaign campaign;

  public Add(String worldName, Campaign campaign) {
    this.worldName = worldName;
    this.campaign = campaign;
  }

  public Add(Campaign campaign) {
    this(null, campaign);
  }

  public static Add content(Campaign campaign) {
    return new Add(campaign);
  }

  public Add to(String worldName) {
    return new Add(worldName, campaign);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    FateService service = UseTheService.as(actor).getService();
    World world = service.getWorldInfo(this.worldName);
    service.createContent(campaign, world);
  }

}
