package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.CabinetAppService;
import com.example.cabinetmedical.application.service.RendezVousAppService;
import com.example.cabinetmedical.application.service.SecretaireAppService;

import java.util.List;

import com.example.cabinetmedical.domain.utils.RendezVousState;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    private final SecretaireAppService secretaireAppService;
    private final RendezVousAppService rendezVousAppService;
    private AuthService authService  ;
    private CabinetAppService cabinetAppService;



    public RendezVousController(SecretaireAppService secretaireAppService, 
        AuthService authService ,
        RendezVousAppService rendezVousAppService, CabinetAppService cabinetAppService) {
        this.secretaireAppService = secretaireAppService;
        this.rendezVousAppService = rendezVousAppService;
        this.authService =authService ;
        this.cabinetAppService=cabinetAppService;
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


    
    @GetMapping("/patient/{idRendezVous}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> getRendezVousInfo(
        Authentication aut, 
        @PathVariable("idRendezVous") int idRendezVous) {
       UserDTO user = authService.getUserDto(aut) ;
       RendezVousDTO rendezVousDTO = rendezVousAppService.getRendezVousById(idRendezVous  , user) ;

       ApiResponse<RendezVousDTO> response = ApiResponse.<RendezVousDTO>builder()
                .data(rendezVousDTO)
                .message("succes")
                .status(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    //* Ayman  */
     
    // creer
    @PostMapping("/secretaire")
    public ResponseEntity<ApiResponse<RendezVousDTO>> createRendezVous(
            @RequestBody RendezVousDTO rendezVousDTO,
            Authentication auth) {

        System.out.println("Received RVDTO: " +  rendezVousDTO);

        UserDTO user = authService.getUserDto(auth);
        RendezVousDTO created = rendezVousAppService.createRendezVous(user, rendezVousDTO);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Rendez-vous créé avec succès",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //mise a jour
    @PutMapping("/secretaire/{idRendezVous}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> updateRendezVous(
            @PathVariable int idRendezVous,
            @RequestBody RendezVousDTO rendezVousDTO,
            Authentication auth) {

        UserDTO user = authService. getUserDto(auth);
        RendezVousDTO updated = rendezVousAppService.updateRendezVous(user, idRendezVous, rendezVousDTO);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous mis à jour avec succès",
                updated
        );
        return ResponseEntity.ok(response);
    }

    //supprimer
    @DeleteMapping("/secretaire/{idRendezVous}")
    public ResponseEntity<ApiResponse<Void>> deleteRendezVous(
            @PathVariable int idRendezVous,
            Authentication auth) {

        UserDTO user = authService.getUserDto(auth);
        rendezVousAppService.deleteRendezVous(user, idRendezVous);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous supprimé avec succès",
                null
        );
        return ResponseEntity.ok(response);
    }

    //consulter
    @GetMapping("/secretaire/{idRendezVous}")
    public ResponseEntity<ApiResponse<RendezVousDTO>> getRendezVousById(
            @PathVariable int idRendezVous,
            Authentication auth) {

        UserDTO user = authService.getUserDto(auth);
        RendezVousDTO rendezVous = rendezVousAppService. getRendezVousById(user, idRendezVous);
        ApiResponse<RendezVousDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous récupéré avec succès",
                rendezVous
        );
        return ResponseEntity.ok(response);
    }

//sans permissions

    //get all
    @GetMapping("/secretaire/getrvs")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getAllRendezVousByCabinet(
            Authentication auth) {

        UserDTO user = authService.getUserDto(auth);
        int idCabinet = cabinetAppService.getCabinetByEmail(user).getIdCabinet();
        List<RendezVousDTO> rendezVousList = rendezVousAppService.getAllRendezVousByCabinet(idCabinet, user);
        System.out.println("SENT RENDEZ VOUS: " + rendezVousList);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des rendez-vous récupérée avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }

    //d un cabinet
    @GetMapping("/secretaire/patient/{idPatient}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByPatient(
            @PathVariable int idPatient,
            Authentication auth) {

        UserDTO user = authService.getUserDto(auth);
        List<RendezVousDTO> rendezVousList = rendezVousAppService.getRendezVousByPatient(idPatient, user);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous du patient récupérés avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }

    //par statut
    @GetMapping("/secretaire/statut/{statut}/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<List<RendezVousDTO>>> getRendezVousByStatut(
            @PathVariable String statut,
            @PathVariable int idCabinet,
            Authentication auth) {

        UserDTO user = authService.getUserDto(auth);
        RendezVousState state = RendezVousState.valueOf(statut.toUpperCase());

        List<RendezVousDTO> rendezVousList = rendezVousAppService.getRendezVousByStatut(state, idCabinet, user);
        ApiResponse<List<RendezVousDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Rendez-vous filtrés par statut récupérés avec succès",
                rendezVousList
        );
        return ResponseEntity.ok(response);
    }
}
