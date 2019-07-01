package com.headissue.fate.model;

import java.util.Set;

public interface HasAspects {
  Set<Aspect> getAspects();

  void setAspects(Set<Aspect> aspects);
}
