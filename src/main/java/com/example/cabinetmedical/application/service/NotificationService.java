package com.example.cabinetmedical.application.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void consultationReadyNotification(String emailUser, String messageContent) {

        
    Map<String, Object> payload = new HashMap<>();
    payload.put("message", messageContent);
    payload.put("status", "success");
    payload.put("sentAt", System.currentTimeMillis());

    messagingTemplate.convertAndSendToUser(
        emailUser, 
        "/queue/notifications", 
        payload
    );
}
}
