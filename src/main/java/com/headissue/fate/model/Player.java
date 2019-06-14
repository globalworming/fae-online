package com.headissue.fate.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Player extends AuditModel{

  public static final Player TEST_GAME_MASTER;

  static {
    TEST_GAME_MASTER = new Player();
    TEST_GAME_MASTER.setId(0L);
    TEST_GAME_MASTER.setName("player 0");
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(name, player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        '}';
  }
}
