package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MeasurableAspect extends AuditModel {

  @OneToOne
  private Aspect aspect;

  private int level;

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Aspect getAspect() {
    return aspect;
  }

  public void setAspect(Aspect aspect) {
    this.aspect = aspect;
  }
}
