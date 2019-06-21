package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Mook extends HasAspects {

  private String name;

  private String goodAt;

  private String badAt;

  private int stress;

  @ManyToOne
  private Character belongingTo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGoodAt() {
    return goodAt;
  }

  public void setGoodAt(String goodAt) {
    this.goodAt = goodAt;
  }

  public String getBadAt() {
    return badAt;
  }

  public void setBadAt(String badAt) {
    this.badAt = badAt;
  }

  public Character getBelongingTo() {
    return belongingTo;
  }

  public void setBelongingTo(Character belongingTo) {
    this.belongingTo = belongingTo;
  }
}
