package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Scene extends AuditModel implements IsContent, HasName, IsContainer, HasCharacters, HasAspects {

  private String name;

  @OneToOne
  private Scenario scenario;
  @OneToMany
  private Set<Character> characters = new HashSet<>();
  @OneToMany
  private Set<Aspect> aspects = new HashSet<>();

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
    scenario = (Scenario) container;
  }

  @Override
  public Set<Character> getCharacters() {
    return characters;
  }

  @Override
  public void setCharacters(Set<Character> characters) {
    this.characters = characters;
  }

  @Override
  public Set<Aspect> getAspects() {
    return aspects;
  }

  @Override
  public void setAspects(Set<Aspect> aspects) {
    this.aspects = aspects;
  }
}
