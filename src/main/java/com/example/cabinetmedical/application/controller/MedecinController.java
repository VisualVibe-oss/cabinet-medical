package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.DTO.DepenceDTO;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.Stats.StatsDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.application.service.MedecinAppService;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MedecinController {

    MedecinAppService medecinAppService;

    public MedecinController(MedecinAppService medecinAppService) {
        this.medecinAppService = medecinAppService;
    }

    @PostMapping("/addSecretary")
    public ApiResponse<SecretaireDTO> addSecretary(@RequestBody SecretaireDTO secretaireDTO) {
        Object result = medecinAppService.addSecretary(secretaireDTO);

        if (result instanceof SecretaireDTO secretaire) {
            String message = "Secretary: " + secretaire.getIdSecretaire() + " added for: " + secretaireDTO.getIdCabinet();
            return new ApiResponse<>(HttpStatus.OK.value(), message, secretaire);
        } else if (result instanceof String msg) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), msg, null);
        } else {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error", null);
        }
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

    @PutMapping("/editSecretary")
    public ApiResponse<SecretaireDTO> updateSecretaire(@RequestBody SecretaireDTO secretaireDTO) {
        System.out.println("received dto" + secretaireDTO);

        SecretaireDTO secretaire = medecinAppService.updateSecretaire(secretaireDTO);
        String message = "Secretary: " + secretaire.getIdSecretaire() + "updated for : " + secretaireDTO.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, secretaire);
    }
    @DeleteMapping("/deleteSecretary/{idsecretaire}")
    public ApiResponse<SecretaireDTO> deleteSecretaire(@PathVariable int idsecretaire) {
        System.out.println("received id:  " + idsecretaire);
        medecinAppService.deleteSecretaire(idsecretaire);
        String message = "deleted secretary: " + idsecretaire;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, null);
    }

    @GetMapping("/rendezvous/{idCabinet}")
    public ApiResponse<List<RendezVousDTO>> getAllRendezVous(@PathVariable int idCabinet) {
        List<RendezVousDTO> data = medecinAppService.getAllRendezVous(idCabinet);
        String message = "Appointments retrieved by" + idCabinet;
        int status = HttpStatus.OK.value();
        return new ApiResponse<>(status, message, data);
    }

    @PutMapping("/editRendezVous")
    public ApiResponse<RendezVousDTO> updateRendezVous(@RequestBody RendezVousDTO rendezVousDTO) {

        RendezVousDTO rv = medecinAppService.editRendezVous(rendezVousDTO);
        String message = "Appointment: "+ rendezVousDTO.getIdRendezVous() + " updated for: " + rendezVousDTO.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, rv);
    }

    @GetMapping("/getStats/{idCabinet}")
    public ApiResponse<StatsDTO>  getStats(@PathVariable int idCabinet) {

        StatsDTO stats = medecinAppService.getStats(idCabinet);
        String message = "Stats retrieved by" + idCabinet;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, stats);

    }
    @PostMapping("/addDepence")
    public ApiResponse<DepenceDTO> addDepence(@RequestBody DepenceDTO dto) {

        System.out.println("received dto" + dto);

        DepenceDTO depence = medecinAppService.addDepence(dto);
        String message = "Depence added for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, depence);

    }
    @DeleteMapping("/deleteDepence")
    public ApiResponse<DepenceDTO> deleteDepence(@RequestBody DepenceDTO dto) {
        System.out.println("received dto" + dto);

        medecinAppService.deleteDepence(dto);
        String message = "Depence deleted for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, null);

    }
    @PutMapping("/editDepence")
    public ApiResponse<DepenceDTO> editDepence(@RequestBody DepenceDTO dto) {

        System.out.println("Received Depence" + dto);

        DepenceDTO depence = medecinAppService.updateDepence(dto);
        String message = "Depence updated for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, depence);
    }

}
