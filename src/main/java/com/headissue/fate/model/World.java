package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Objects;
import java.util.Set;

@Entity
public class World extends HasCharactersAndAspects {
    
   private String name;
   private String description;

   @OneToOne
   private Player gameMaster;

  public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

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
}
