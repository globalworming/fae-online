package com.headissue.fate.model;

public class EditWorldMessage {
  private MessageType messageType;
  private String content;

  public enum MessageType {
    UPDATE_WORLD,
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
