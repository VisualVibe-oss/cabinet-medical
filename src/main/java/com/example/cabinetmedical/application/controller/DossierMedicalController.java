package com.example.cabinetmedical.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cabinetmedical.application.DTO.DossierMedicalDetailDTO;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.service.DossierMedicalService;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/dossierMedical")
public class DossierMedicalController {


    DossierMedicalService dossierMedicalService ;

    DossierMedicalController(DossierMedicalService dossierMedicalService){
        this.dossierMedicalService = dossierMedicalService ;
    }

    private UserDTO getUserDto(Authentication authentication) {

        String email = (String) authentication.getPrincipal();

        // 2. Récupérer le rôle et supprimer "ROLE_"
        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElse("NONE");
        UserDTO user = new UserDTO() ;
        user.setEmail(email);
        user.setRole(role);

        
        return user ; 
    }

  

    @GetMapping("/{idRendezVous}")
    public ResponseEntity<ApiResponse<DossierMedicalDetailDTO>> getDossierMedicalInfo(Authentication authentication , @PathVariable int idRendezVous) {
        UserDTO user = getUserDto(authentication);
        DossierMedicalDetailDTO detailDTO = dossierMedicalService.getDossierMedicalDetail( user , idRendezVous) ;


        ApiResponse<DossierMedicalDetailDTO> response = ApiResponse.<DossierMedicalDetailDTO>builder()
                .data(detailDTO)
                .message("succes")
                .status(HttpStatus.CREATED.value())
                .build();


        return ResponseEntity.ok(response);
    }
    
}
