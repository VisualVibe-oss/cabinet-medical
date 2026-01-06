package com.example.cabinetmedical.application.controller.medicament;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.Medicament.MedicamentDTO;
import com.example.cabinetmedical.application.service.medicament.MedicamentAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/medicaments")
public class MedicamentController {
    private final MedicamentAppService appService;

    public MedicamentController(MedicamentAppService medicamentAppService) {
        this.appService = medicamentAppService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicamentDTO>>> listAll(){
        try{
            List<MedicamentDTO> medicaments=appService.listAll();

            ApiResponse<List<MedicamentDTO>> response=new ApiResponse<>(200,"Medicaments recuperes",medicaments);
            return  ResponseEntity.ok(response);
        }catch(Exception e){
            ApiResponse<List<MedicamentDTO>> response=new ApiResponse<>(500,"Erreur en recuperation des medicaments",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity <ApiResponse<MedicamentDTO>> oneMedicament(@PathVariable Integer id){
        try{
            MedicamentDTO medicament=appService.oneMedicament(id);

            ApiResponse<MedicamentDTO> response=new ApiResponse<>(200,"Medicament recupere",medicament);
            return ResponseEntity.ok(response);
        }catch(IllegalArgumentException e){
            ApiResponse<MedicamentDTO> response=new ApiResponse<>(404,"Medicament non trouve",null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ApiResponse<MedicamentDTO> response=new ApiResponse<>(500,"Errer lors de la recuperation ",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<MedicamentDTO>> create(@RequestBody MedicamentDTO medicament) {
        try {
            MedicamentDTO medicamentDTO = appService.create(medicament);

            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    201,
                    "Médicament cree ",
                    medicamentDTO
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    400,
                    "Donnees invaldes",
                    null
            );
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    500,"Erreur lors de la creation du medicament: " + e.getMessage(),null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicamentDTO>> update(
            @PathVariable Integer id,
            @RequestBody MedicamentDTO medicament) {
        try {
            MedicamentDTO updated = appService.updateMedicament(id, medicament);

            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    200,
                    "Medicament mis a jour avec succes",
                    updated
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    404,
                    "Medicament non trouvé",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    500,
                    "Erreur lors de la mise a jour ",
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            appService.deleteMedicament(id);

            ApiResponse<Void> response = new ApiResponse<>(
                    200,
                    "Medicament supprime avec succes",
                    null
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    404,
                    "Medicament non trouve ",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    500,
                    "Erreur lors de la suppression ",
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/recherche")
    public ResponseEntity<ApiResponse<MedicamentDTO>> search(@RequestParam String nom) {
        try {
            MedicamentDTO medicament = appService.findMedicament(nom);

            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    200,
                    "Medicament trouve",
                    medicament
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    404,
                    "Medicament non trouve",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ApiResponse<MedicamentDTO> response = new ApiResponse<>(
                    500,
                    "Erreur lors de la recherche du medicament",
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
