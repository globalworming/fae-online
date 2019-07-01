package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Scenario extends HasCharactersAndAspects implements IsContent, HasName, IsContainer {

  @OneToOne
  private Campaign campaign;

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
  public IsContainer getContainer() {
    return campaign;
  }

  @Override
  public void setContainer(IsContainer container) {

  }
}
