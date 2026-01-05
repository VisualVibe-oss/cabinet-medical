package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.service.SecretaireAppService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendezvous")
public class SecretaireController {

    private final SecretaireAppService secretaireAppService;

    public SecretaireController(SecretaireAppService secretaireAppService) {
        this.secretaireAppService = secretaireAppService;
    }

    @PostMapping("/create/{idSecretaire}")
    public ApiResponse<RendezVousDTO> creeRendezVous(@RequestParam int idSecretaire, @RequestBody RendezVousDTO rvdto) {

        RendezVousDTO rv = secretaireAppService.creeRendezVous(idSecretaire, rvdto);
        String message = "Rendez-Vous: " + rv.getIdRendezVous() + "Created By: " + idSecretaire;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, rv);
    }
}

