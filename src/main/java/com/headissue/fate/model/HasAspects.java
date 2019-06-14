package com.headissue.fate.model;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@MappedSuperclass
public abstract class HasAspects extends AuditModel {

  @OneToMany
  private Set<Aspect> aspects;

  public Set<Aspect> getAspects() {
    return aspects;
  }

  public void setAspects(Set<Aspect> aspects) {
    this.aspects = aspects;
  }
}
