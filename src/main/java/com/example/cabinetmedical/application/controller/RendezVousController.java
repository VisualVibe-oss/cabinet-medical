package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.dto.RendezVousDTO;
import com.example.cabinetmedical.application.service.SecretaireAppService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    private final SecretaireAppService secretaireAppService;

    public  RendezVousController(SecretaireAppService secretaireAppService) {
        this.secretaireAppService = secretaireAppService;
    }

    @PostMapping("/create/{idSecretaire}")
        public RendezVousDTO creeRendezVous(@RequestParam int idSecretaire, @RequestBody RendezVousDTO rvdto){
            return secretaireAppService.creeRendezVous(idSecretaire, rvdto);
        }
    }

