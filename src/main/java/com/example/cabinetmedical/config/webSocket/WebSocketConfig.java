package com.example.cabinetmedical.config.webSocket;

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
        // L'URL de connexion : ws://localhost:8080/ws-cabinet
        registry.addEndpoint("/ws-cabinet")
                .setAllowedOriginPatterns("*") // À restreindre en production (ex: "http://localhost:4200")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 1. Les canaux que le serveur utilise pour envoyer (Diffusion)
        // /topic pour le broadcast, /queue pour les messages ciblés
        config.enableSimpleBroker("/topic", "/queue"); 
        
        // 2. Le préfixe pour que les clients appellent les méthodes annotées @MessageMapping dans tes Controllers
        config.setApplicationDestinationPrefixes("/app");
        
        // 3. Le préfixe pour les messages privés (nécessaire pour SimpMessagingTemplate.convertAndSendToUser)
        config.setUserDestinationPrefix("/user");
    }
}