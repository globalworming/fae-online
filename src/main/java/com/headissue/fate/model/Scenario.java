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
  private Set<Character> characters = new HashSet<>();

  @OneToMany
  private Set<Aspect> aspects = new HashSet<>();

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
