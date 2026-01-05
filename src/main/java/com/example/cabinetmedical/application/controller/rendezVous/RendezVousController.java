package com.example.cabinetmedical.application.controller.rendezVous;


import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.rendezVous.RendezVousDTO;
import com.example.cabinetmedical.application.service.rendezVous.RendezVousAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secretaire/rendezvous")
public class RendezVousController {

    private final RendezVousAppService rendezVousAppService;

    public RendezVousController(RendezVousAppService rendezVousAppService) {
        this.rendezVousAppService = rendezVousAppService;
    }

    // creer
    @PostMapping
    public ResponseEntity<ApiResponse<RendezVousDTO>> createRendezVous(
            @RequestHeader("X-Secretaire-Id") int idSecretaire,
            @RequestBody RendezVousDTO rendezVousDTO) {

        RendezVousDTO created = rendezVousAppService. createRendezVous(idSecretaire, rendezVousDTO);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Rendez-vous créé avec succès",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //mise a jour
    @PutMapping("/{idRendezVous}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> updateRendezVous(
            @RequestHeader("X-Secretaire-Id") int idSecretaire,
            @PathVariable int idRendezVous,
            @RequestBody RendezVousDTO rendezVousDTO) {

        RendezVousDTO updated = rendezVousAppService.updateRendezVous(idSecretaire, idRendezVous, rendezVousDTO);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous mis à jour avec succès",
                updated
        );
        return ResponseEntity.ok(response);
    }

    //supprimer
    @DeleteMapping("/{idRendezVous}")
    public ResponseEntity<ApiResponse<Void>> deleteRendezVous(
            @RequestHeader("X-Secretaire-Id") int idSecretaire,
            @PathVariable int idRendezVous) {

        rendezVousAppService.deleteRendezVous(idSecretaire, idRendezVous);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous supprimé avec succès",
                null
        );
        return ResponseEntity.ok(response);
    }

    //consulter
    @GetMapping("/{idRendezVous}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> getRendezVousById(
            @RequestHeader("X-Secretaire-Id") int idSecretaire,
            @PathVariable int idRendezVous) {

        RendezVousDTO rendezVous = rendezVousAppService.getRendezVousById(idSecretaire, idRendezVous);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous récupéré avec succès",
                rendezVous
        );
        return ResponseEntity.ok(response);
    }

    //sans permissions

    //get all
    @GetMapping("/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getAllRendezVousByCabinet(
            @PathVariable int idCabinet) {

        List<RendezVousDTO> rendezVousList = rendezVousAppService.getAllRendezVousByCabinet(idCabinet);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des rendez-vous récupérée avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }

    //d un cabinet
    @GetMapping("/patient/{idPatient}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByPatient(
            @PathVariable int idPatient) {

        List<RendezVousDTO> rendezVousList = rendezVousAppService.getRendezVousByPatient(idPatient);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous du patient récupérés avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }

    //par statut
    @GetMapping("/statut/{statut}/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByStatut(
            @PathVariable String statut,
            @PathVariable int idCabinet) {

        List<RendezVousDTO> rendezVousList = rendezVousAppService.getRendezVousByStatut(statut, idCabinet);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous filtrés par statut récupérés avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }
}
