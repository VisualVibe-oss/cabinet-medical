package com.example.cabinetmedical.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.application.dto.FactureDTO;

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
    public void facturePendingNotification(List<String> emails, FactureDTO factureDTO) {

        if (factureDTO == null) {
            throw new IllegalArgumentException("FactureDTO ne peut pas être null");
        }
        for (String email : emails) {
            messagingTemplate.convertAndSendToUser(
            email, 
            "/facture/pending", 
            factureDTO
        );
        }
        

    }
}
