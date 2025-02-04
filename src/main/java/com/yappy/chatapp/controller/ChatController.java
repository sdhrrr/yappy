package com.yappy.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.yappy.chatapp.model.ChatMessage;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatMessage.setContent(chatMessage.getSender() + " joined the chat");
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        return chatMessage;
    }

    @MessageMapping("/chat.removeUser")
    @SendTo("topic/public")
    public ChatMessage removeUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        chatMessage.setContent(chatMessage.getSender() + " left the chat");
        chatMessage.setType(ChatMessage.MessageType.LEAVE);
        return chatMessage;        
    }
}
