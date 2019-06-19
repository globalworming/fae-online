package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Campaign extends HasCharactersAndAspects {
    
  @OneToOne
  private World world;

  @OneToMany
  private Set<Scenario> scenario;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public Set<Scenario> getScenario() {
    return scenario;
  }

  public void setScenario(Set<Scenario> scenario) {
    this.scenario = scenario;
  }
}
