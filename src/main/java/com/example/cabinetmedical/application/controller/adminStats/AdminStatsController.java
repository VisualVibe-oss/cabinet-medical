package com.example.cabinetmedical.application.controller.adminStats;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.cabinetStats.CabinetStatsDTO;
import com.example.cabinetmedical.application.service.adminStats.AdminStatsAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminStatsController {
    private final AdminStatsAppService appService;
    public AdminStatsController(AdminStatsAppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CabinetStatsDTO>> cabinetStats(){
        try{
            CabinetStatsDTO stats =appService.getCabinetStats();

            ApiResponse<CabinetStatsDTO> response = new ApiResponse<>(200,"stats recuperes",stats);
            return ResponseEntity.ok(response);
        }catch (IllegalStateException e) {
            ApiResponse<CabinetStatsDTO> response = new ApiResponse<>(400, "Donnees invalides:  " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ApiResponse<CabinetStatsDTO> response = new ApiResponse<>(500, "Erreur serveur: " + e.getMessage(), null);
            return ResponseEntity. status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
