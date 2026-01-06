package com.example.cabinetmedical.application.controller.cabinetEtat;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.cabinetEtat.CabinetEtatDTO;
import com.example.cabinetmedical.application.dto.cabinetEtat.CabinetEtatRequestDTO;
import com.example.cabinetmedical.application.dto.cabinetEtat.CabinetEtatResponseDTO;
import com.example.cabinetmedical.application.service.cabinetEtat.CabinetEtatAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard/cabinets")
public class CabinetEtatController {
    private final CabinetEtatAppService service;
    public CabinetEtatController(CabinetEtatAppService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<ApiResponse<List<CabinetEtatDTO>>> getAllCabinets(){
        List<CabinetEtatDTO> cabinets=service.getAllCabinets();
        ApiResponse<List<CabinetEtatDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des cabinets recuperes avec succes",
                cabinets
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/actifs")
    public ResponseEntity<ApiResponse<List<CabinetEtatDTO>>> getCabinetsActifs() {
        List<CabinetEtatDTO> cabinets = service.getCabinetsActifs();
        ApiResponse<List<CabinetEtatDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des cabinets actifs recuperres avec succes",
                cabinets
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactifs")
    public ResponseEntity<ApiResponse<List<CabinetEtatDTO>>> getCabinetsInactifs() {
        List<CabinetEtatDTO> cabinets = service. getCabinetsInactifs();
        ApiResponse<List<CabinetEtatDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des cabinets inactifs recuperes avec succes",
                cabinets
        );
        return ResponseEntity. ok(response);
    }

    @PutMapping("/{id}/etat")
    public ResponseEntity<ApiResponse<CabinetEtatResponseDTO>> changerEtat(
            @PathVariable int id,
            @RequestBody CabinetEtatRequestDTO request) {

        CabinetEtatResponseDTO result = service.changerEtat(id, request);
        ApiResponse<CabinetEtatResponseDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Etat modifie avec succes",
                result
        );
        return ResponseEntity.ok(response);
    }
}
