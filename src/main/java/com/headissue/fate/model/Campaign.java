package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Campaign extends HasCharactersAndAspects implements IsContent, HasName, IsContainer {
    
  @OneToOne
  private World container;

  private String name;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public World getContainer() {
    return container;
  }

  @Override
  public void setContainer(IsContainer container) {
    this.container = (World) container;
  }

}
