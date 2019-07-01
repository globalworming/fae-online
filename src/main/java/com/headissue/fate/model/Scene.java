package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Scene extends HasCharactersAndAspects  implements IsContent, HasName, IsContainer {

  private String name;

  @OneToOne
  private Scenario scenario;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public IsContainer getContainer() {
    return scenario;
  }

  @Override
  public void setContainer(IsContainer container) {

  }
}
