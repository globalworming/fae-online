package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Scene extends HasCharactersAndAspects {

  private String name;

  @OneToOne
  private Scenario scenario;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Scenario getScenario() {
    return scenario;
  }

  public void setScenario(Scenario scenario) {
    this.scenario = scenario;
  }
}
