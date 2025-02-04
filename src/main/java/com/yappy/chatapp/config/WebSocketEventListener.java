package com.yappy.chatapp.config;

import com.yappy.chatapp.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            logger.info(username + " disconnected");
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(username);
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setContent(username + " left the chat");

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(BrokerAvailabilityEvent event) {
        logger.info("WebSocket Broker available: " + event.isBrokerAvailable());
    }

}
