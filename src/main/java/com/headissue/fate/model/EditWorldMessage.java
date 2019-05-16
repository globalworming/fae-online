package com.headissue.fate.model;

public class EditWorldMessage {
  private MessageType type;
  private String content;

  public enum MessageType {
    UPDATE_DESCRIPTION,
  }

  public MessageType getType() {
    return type;
  }

  public void setType(MessageType type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
