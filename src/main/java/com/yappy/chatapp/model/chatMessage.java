package com.yappy.chatapp.model;

public class chatMessage {
    private String sender;
    private String message;
    private MessageType type;

    public enum MessageType{
        CHAT, JOIN, LEAVE
    }

    // getters and setters for the chatMessage object
    

}
