package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Scenario extends AuditModel implements IsContent, HasName, IsContainer, HasAspects, HasCharacters {

  @OneToOne
  private Campaign campaign;

  private String name;

  @OneToMany
  private Set<Actor> actors = new HashSet<>();

  @OneToMany
  private Set<Aspect> aspects = new HashSet<>();

  @OneToMany
  private Set<Scene> content = new HashSet<>();

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
    campaign = (Campaign) container;
  }

  @Override
  public Set<Actor> getActors() {
    return actors;
  }

  @Override
  public void setActors(Set<Actor> actors) {
    this.actors = actors;
  }

  @Override
  public Set<Aspect> getAspects() {
    return aspects;
  }

  @Override
  public void setAspects(Set<Aspect> aspects) {
    this.aspects = aspects;
  }

  @Override
  public Set<Scene> getContent() {
    return content;
  }

  @Override
  public void setContent(Set<? extends IsContent> content) {
    this.content = (Set<Scene>) content;
  }
}
