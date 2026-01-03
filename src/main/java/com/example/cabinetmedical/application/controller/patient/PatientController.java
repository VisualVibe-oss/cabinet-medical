package com.example.cabinetmedical.application.controller.patient;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.dto.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.application.dto.patient.PatientDTO;
import com.example.cabinetmedical.application.service.patient.PatientAppService;
import com.example.cabinetmedical.infrastructure.repository.patient.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secretaire/patients")
public class PatientController {
    private final PatientAppService patientAppService;

    public PatientController(PatientAppService patientAppService) {
        this.patientAppService = patientAppService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientAppService.createPatient(patientDTO);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Patient créé avec succès",
                createdPatient
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les patients d'un cabinet
     */
    @GetMapping("/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getAllPatientsByCabinet(@PathVariable int idCabinet) {
        List<PatientDTO> patients = patientAppService.getAllPatientsByCabinet(idCabinet);
        ApiResponse<List<PatientDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Liste des patients récupérée avec succès",
                patients
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Rechercher des patients par nom
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PatientDTO>>> searchPatientsByNom(@RequestParam String nom,@RequestParam int idCabinet) {
        List<PatientDTO> patients = patientAppService.getPatientsByNomAndCabinet(nom,idCabinet);
        ApiResponse<List<PatientDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patients trouvés avec succès",
                patients
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/cin/{cin}/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientByCin(@PathVariable String cin,@PathVariable int idCabinet) {
        PatientDTO patient = patientAppService.getPatientByCinAndCabinet(cin,idCabinet);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient trouvé avec succès",
                patient
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer un patient par son ID
     */
    @GetMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(@PathVariable int idPatient) {
        PatientDTO patient = patientAppService.getPatientById(idPatient);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient récupéré avec succès",
                patient
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un patient
     */
    @PutMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(
            @PathVariable int idPatient,
            @RequestBody PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientAppService. updatePatient(idPatient, patientDTO);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient mis à jour avec succès",
                updatedPatient
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un patient
     */
    @DeleteMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable int idPatient) {
        patientAppService.deletePatient(idPatient);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient supprimé avec succès",
                null
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Associer un dossier médical à un patient
     */
    @PostMapping("/{idPatient}/dossier")
    public ResponseEntity<ApiResponse<DossierMedicalDTO>> associateDossierMedical(
            @PathVariable int idPatient,
            @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO dossier = patientAppService.associateDossierMedical(idPatient, dossierDTO);
        ApiResponse<DossierMedicalDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Dossier médical associé avec succès",
                dossier
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Mettre à jour le dossier médical
     */
    @PutMapping("/{idPatient}/dossier")
    public ResponseEntity<ApiResponse<DossierMedicalDTO>> updateDossierMedical(
            @PathVariable int idPatient,
            @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO dossier = patientAppService. updateDossierMedical(idPatient, dossierDTO);
        ApiResponse<DossierMedicalDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Dossier médical mis à jour avec succès",
                dossier
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer le dossier médical d'un patient
     */
    @GetMapping("/{idPatient}/dossier")
    public ResponseEntity<ApiResponse<DossierMedicalDTO>> getDossierMedical(@PathVariable int idPatient) {
        DossierMedicalDTO dossier = patientAppService.getDossierMedical(idPatient);
        ApiResponse<DossierMedicalDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Dossier médical récupéré avec succès",
                dossier
        );
        return ResponseEntity.ok(response);
    }
}
