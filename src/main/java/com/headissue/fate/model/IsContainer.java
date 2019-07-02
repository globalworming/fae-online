package com.headissue.fate.model;

import java.util.Set;

public interface IsContainer {

  Set<? extends IsContent> getContent();

  void setContent(Set<? extends IsContent> content);

}
