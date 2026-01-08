package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.SecretaireAppService;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rendezvous")
public class SecretaireController {

    private final SecretaireAppService secretaireAppService;
    private final SpringSecretaireRepository secretaireRepository;
    private final AuthService authService;

    public SecretaireController(SecretaireAppService secretaireAppService,SpringSecretaireRepository secretaireRepository, AuthService authService) {
        this.secretaireAppService = secretaireAppService;
        this.secretaireRepository = secretaireRepository;
        this.authService = authService;
    }

    @PostMapping("/create/{idSecretaire}")
    public ApiResponse<RendezVousDTO> creeRendezVous(@RequestParam int idSecretaire, @RequestBody RendezVousDTO rvdto) {

        RendezVousDTO rv = secretaireAppService.creeRendezVous(idSecretaire, rvdto);
        String message = "Rendez-Vous: " + rv.getIdRendezVous() + "Created By: " + idSecretaire;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, rv);
    }

    @GetMapping("/auth/permissions")
    public ResponseEntity<List<String>> getPermissionsAfterLogin(Authentication auth) {
        UserDTO user = authService.getUserDto(auth);


        // 1. Get entity from DB
        SecretaireEntity secretaire = secretaireRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));


        // 3. Convert PermissionKey Set to List<String>
        List<String> permissions = secretaire.getPermissionKeys().stream()
                .map(Enum::name)
                .collect(Collectors.toList());



        return ResponseEntity.ok(permissions);
    }
}

