package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.UserDTO;
import com.example.cabinetmedical.application.service.RendezVousAppService;
import com.example.cabinetmedical.application.service.SecretaireAppService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    private final SecretaireAppService secretaireAppService;
    private final RendezVousAppService rendezVousAppService;

    public RendezVousController(SecretaireAppService secretaireAppService, RendezVousAppService rendezVousAppService) {
        this.secretaireAppService = secretaireAppService;
        this.rendezVousAppService = rendezVousAppService;
    }

    @PostMapping("/create/{idSecretaire}")
    public RendezVousDTO creeRendezVous(@RequestParam int idSecretaire, @RequestBody RendezVousDTO rvdto) {
        return secretaireAppService.creeRendezVous(idSecretaire, rvdto);
    }

    @PostMapping("/search") // On change le verbe en POST
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> searchRendezVous(@RequestBody UserDTO user) {
        List<RendezVousDTO> RendezVousList = rendezVousAppService.getRendezVous(user);
        ApiResponse<List<RendezVousDTO>> response = ApiResponse.<List<RendezVousDTO>>builder()
                .data(RendezVousList)
                .message("succes")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

}
