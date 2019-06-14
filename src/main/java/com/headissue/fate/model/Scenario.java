package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Scenario extends HasCharactersAndAspects {

  @OneToOne
  private Campaign campaign;

  @OneToMany
  private Set<Scene> scenes;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Set<Scene> getScenes() {
    return scenes;
  }

  public void setScenes(Set<Scene> scenes) {
    this.scenes = scenes;
  }
}
