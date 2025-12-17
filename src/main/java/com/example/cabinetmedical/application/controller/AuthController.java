package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.dto.AuthResponse;
import com.example.cabinetmedical.application.dto.ConnexionRequest;
import com.example.cabinetmedical.application.dto.MedecinInscriptionRequest;
import com.example.cabinetmedical.application.dto.SecretaireInscriptionRequest;
import com.example.cabinetmedical.application.service.ConnexionService;
import com.example.cabinetmedical.application.service.InscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final ConnexionService connexionService;
    private final InscriptionService inscriptionService;

    @PostMapping("/connexion")
    public ResponseEntity<AuthResponse> connecter(
            @Valid @RequestBody ConnexionRequest request) {

        try {
            // Appel du service de connexion
            AuthResponse response = connexionService.connecter(request);

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResponse.builder()
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/inscription/medecin")
    public ResponseEntity<AuthResponse> inscrireMedecin(@Valid @RequestBody MedecinInscriptionRequest request){
        try{
            AuthResponse response = inscriptionService.inscrireMedecin(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest()
                    .body(AuthResponse.builder()
                            .message(e.getMessage())
                            .build());
        }

    }
    @PostMapping("/inscription/secretaire")
    public ResponseEntity<AuthResponse> inscrireSecretaire(
            @Valid @RequestBody SecretaireInscriptionRequest request,
            Authentication authentication) {

        try {

            String medecinEmail = authentication.getName();

            Integer medecinId = connexionService.getMedecinIdByEmail(medecinEmail);

            AuthResponse response = inscriptionService.inscrireSecretaire(request, medecinId);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(AuthResponse.builder()
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<String> deconnecter(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            return ResponseEntity.ok("Déconnexion réussie");
        }
        return ResponseEntity.badRequest().body("Aucun utilisateur connecté");
    }
}
