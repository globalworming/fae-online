package com.headissue.fate.model;


import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@MappedSuperclass
public abstract class HasCharactersAndAspects extends HasAspects {
    
  @OneToMany
  private Set<Character> characters;

  public Set<Character> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<Character> characters) {
    this.characters = characters;
  }
}
