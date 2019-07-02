package com.headissue.fate.model;

import java.util.Set;

public interface HasCharacters {
  Set<Actor> getActors();

  void setActors(Set<Actor> actors);
}
