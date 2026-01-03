package com.example.cabinetmedical.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.RequestConsultationDTO;
import com.example.cabinetmedical.application.dto.UserDTO;
import com.example.cabinetmedical.application.service.ConsultationService;

import org.apache.catalina.connector.Request;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/consultation")
public class ConsultationController {

    ConsultationService consultationService;



    ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }   

    private UserDTO getUserDto(Authentication authentication) {

        String email = (String) authentication.getPrincipal();

        // 2. Récupérer le rôle et supprimer "ROLE_"
        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElse("NONE");
        UserDTO user = new UserDTO();
        user.setEmail(email);
        user.setRole(role);
        return user;
    }

    @PostMapping("/{idRendezVous}")
    public ResponseEntity<ApiResponse<Boolean>> createConsulatation(
            Authentication aut,
            @PathVariable("idRendezVous") int idRendezVous,
            @RequestBody RequestConsultationDTO requestConsultationDTO
        ) {

            UserDTO user = getUserDto(aut);
            consultationService.createConsultation( idRendezVous, user, requestConsultationDTO);
            ApiResponse<Boolean> response = ApiResponse.<Boolean>builder()
                    .data(true)
                    .message("Consultation created successfully")
                    .build();
            return ResponseEntity.ok(response);

    }

}
