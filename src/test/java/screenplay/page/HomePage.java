package screenplay.page;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://localhost:8080")
public class HomePage extends PageObject {
  public static String chat = ".e2e-chat-messages";
}
