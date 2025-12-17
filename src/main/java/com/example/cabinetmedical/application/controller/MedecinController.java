package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.SecretaireDTO;
import com.example.cabinetmedical.application.service.MedecinAppService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class MedecinController {

    MedecinAppService medecinAppService;

    public MedecinController(MedecinAppService medecinAppService) {
        this.medecinAppService = medecinAppService;
    }

    @PostMapping("/addSecretary/{idMedecin}")
    public ApiResponse<SecretaireDTO> addSecretary(@RequestBody SecretaireDTO secretaireDTO, @PathVariable int idMedecin) {

        SecretaireDTO secretaire = medecinAppService.addSecretary(secretaireDTO, idMedecin);
        int status = HttpStatus.OK.value();
        String message = "Secretary: " + secretaire.getIdSecretaire() + " added by: " + idMedecin;

       return new ApiResponse<>(status, message, secretaire);
    }
    @GetMapping("/secretaires/{idCabinet}")
    public ApiResponse<List<SecretaireDTO>> getAllSecretaries(@PathVariable int idCabinet) {

        List<SecretaireDTO> secretaires = medecinAppService.getAllSecretaries(idCabinet);
        String message = "Secretary page accessed for office: " +  idCabinet;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status,message,secretaires);
    }

    @GetMapping("/secretaires/{idCabinet}/permissions")
    public List<String> getAllPermissions() {
        return Arrays.stream(PermissionKey.values())
                .map(PermissionKey::name)
                .collect(Collectors.toList());
    }

    @PutMapping("/editSecretary/{idSecretaire}/edited-by/{idMedecin}")
    public ApiResponse<SecretaireDTO> updateSecretaire(@PathVariable int idSecretaire, @RequestBody SecretaireDTO secretaireDTO, @PathVariable int idMedecin) {

        SecretaireDTO secretaire = medecinAppService.updateSecretaire(idSecretaire,secretaireDTO, idMedecin);
        String message = "Secretary: " + secretaire.getIdSecretaire() + " updated by: " + idMedecin;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, secretaire);
    }
    @DeleteMapping("/deleteSecretary/{idsecretaire}/deleted-by/{idMedcin}")
    public ApiResponse<SecretaireDTO> deleteSecretaire(@PathVariable int idsecretaire, @PathVariable int idMedcin) {
        medecinAppService.deleteSecretaire(idsecretaire, idMedcin);
        String message = "Secretary: " + idsecretaire + " deleted by: " + idMedcin;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, null);
    }

}
