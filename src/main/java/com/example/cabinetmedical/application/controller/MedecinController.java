package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.dto.SecretaireDTO;
import com.example.cabinetmedical.application.service.MedecinAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedecinController {
    @Autowired
    MedecinAppService medecinAppService;

    public MedecinController(MedecinAppService medecinAppService) {
        this.medecinAppService = medecinAppService;
    }

    @PostMapping("/addSecretary/{idMedecin}")
    public SecretaireDTO addSecretary(@RequestBody SecretaireDTO secretaireDTO, @PathVariable int idMedecin) {
       return medecinAppService.addSecretary(secretaireDTO, idMedecin);
    }
}
