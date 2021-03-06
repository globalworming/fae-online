package com.headissue.fate.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties(
//    value = {"characters", "hibernateLazyInitializer", "handler"}
)

public class World extends AuditModel implements IsContainer, HasName, HasCharacters, HasAspects {


  public World() {
  }

  public World(String name) {
    this.name = name;
  }

  private String description;
  private String name;

  @OneToMany
  private Set<Campaign> content;

  @OneToOne
  private Player gameMaster;

  @OneToMany
  private Set<Actor> actors = new HashSet<>();

  @OneToMany
  private Set<Aspect> aspects = new HashSet<>();

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public Player getGameMaster() {
    return gameMaster;
  }

  public void setGameMaster(Player gameMaster) {
    this.gameMaster = gameMaster;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    World world = (World) o;
    return Objects.equals(name, world.name) &&
        Objects.equals(description, world.description) &&
        Objects.equals(gameMaster, world.gameMaster);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, gameMaster);
  }

  @Override
  public String toString() {
    return "World{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", gameMaster=" + gameMaster +
        '}';
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
  public Set<Campaign> getContent() {
    return content;
  }

  public void setContent(Set<? extends IsContent> content) {
    this.content = (Set<Campaign>) content;
  }
}
