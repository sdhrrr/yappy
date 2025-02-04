package com.yappy.chatapp.model;

import org.apache.logging.log4j.message.Message;

public class chatMessage {
    private String sender;
    private String message;
    private MessageType type;

    public enum MessageType{
        CHAT, JOIN, LEAVE
    }

    // getters and setters for the chatMessage object
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return type;
    }
    public void setMessageType(MessageType type) {
        this.type = type;
    }

}
