package com.yappy.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                // allowing CORS 
                .setAllowedOrigins(
                    "http://localhost:5173",     // Local development ip address
                    "https://curb-chat.vercel.app"        // Vercel deployment ip address
                )
                .withSockJS(); // we use sockjs for the communication
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
        
        registry.setPreservePublishOrder(true);
        registry.setUserDestinationPrefix("/user");

        // endpoints for the connections.
    }
}