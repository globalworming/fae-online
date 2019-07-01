package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class KnowOfAWorld extends QuestionWithDefaultSubject<Boolean> {

  @Override
  public Boolean answeredBy(Actor actor) {
    return actor.recall("WORLD") != null;
  }
}
