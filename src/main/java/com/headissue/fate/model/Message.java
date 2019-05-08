package com.headissue.fate.model;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Message extends AuditModel {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;
  private String sender;
  private String content;
  private long world;


  public Message() {
  }

  public Message(ChatMessage chatMessage) {
    setContent(chatMessage.getContent());
    setSender(chatMessage.getSender());
    setWorld(chatMessage.getWorld());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getWorld() {
    return world;
  }

  public void setWorld(long world) {
    this.world = world;
  }
}
