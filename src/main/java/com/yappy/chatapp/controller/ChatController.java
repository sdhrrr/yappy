package com.yappy.chatapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.yappy.chatapp.model.ChatMessage;

@Controller
public class ChatController {
    
    // Logger for tracking events and errors
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        try {
            logger.info("Message received from {}: {}", chatMessage.getSender(), chatMessage.getContent());
            return chatMessage;
        } catch (Exception e) {
            logger.error("Error processing message from {}: {}", chatMessage.getSender(), e.getMessage());
            throw new RuntimeException("Failed to process message");
        }
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        try {
            // Store username in WebSocket session
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            
            // Set join message
            chatMessage.setContent(chatMessage.getSender() + " joined the chat");
            chatMessage.setType(ChatMessage.MessageType.JOIN);
            
            logger.info("User joined: {}", chatMessage.getSender());
            return chatMessage;
        } catch (Exception e) {
            logger.error("Error adding user {}: {}", chatMessage.getSender(), e.getMessage());
            throw new RuntimeException("Failed to add user");
        }
    }

    @MessageMapping("/chat.removeUser")
    @SendTo("/topic/public")
    public ChatMessage removeUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        try {
            // Set leave message
            chatMessage.setContent(chatMessage.getSender() + " left the chat");
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            
            logger.info("User left: {}", chatMessage.getSender());
            return chatMessage;
        } catch (Exception e) {
            logger.error("Error removing user {}: {}", chatMessage.getSender(), e.getMessage());
            throw new RuntimeException("Failed to remove user");
        }
    }
}