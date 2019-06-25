package com.headissue.fate.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(
    value = {"world"}
)
// chat message
public class Message extends AuditModel {
    private static final long serialVersionUID = 1L;

    private String sender;
    
    private String content;

    private String messageType;

    @OneToOne
    private World world;

    public static class Type {
        public static final String LEAVE = "leave";
        public static String CHAT = "chat";
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return this.sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) &&
            Objects.equals(content, message.content) &&
            Objects.equals(messageType, message.messageType) &&
            Objects.equals(world, message.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, content, messageType, world);
    }

    @Override
    public String toString() {
        return "Message{" +
            "sender='" + sender + '\'' +
            ", content='" + content + '\'' +
            ", messageType='" + messageType + '\'' +
            ", world=" + world +
            '}';
    }
}

