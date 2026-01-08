package com.example.cabinetmedical.application.controller.patient;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.application.DTO.patient.PatientDTO;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.patient.PatientAppService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secretaire/patients")
public class PatientController {
    private final PatientAppService patientAppService;


    private final AuthService authService ; 
    public PatientController(PatientAppService patientAppService ,     AuthService authService  ) {
        this.patientAppService = patientAppService;
        this.authService =  authService ; 

    }

    // ============ OPÉRATIONS NÉCESSITANT DES PERMISSIONS ============

    /**
     * Créer un patient (nécessite permission CREE_PATIENT)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> createPatient(
            @RequestBody PatientDTO patientDTO ,Authentication auth ) {

                
        UserDTO user = authService.getUserDto(auth) ;
        PatientDTO createdPatient = patientAppService.createPatient( user ,  patientDTO);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Patient créé avec succès",
                createdPatient
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Mettre à jour un patient (nécessite permission MODIFIER_PATIENT)
     */
    @PutMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(
            @PathVariable int idPatient,
            @RequestBody PatientDTO patientDTO ,
            Authentication auth ) {

                
        UserDTO user = authService.getUserDto(auth) ;

        PatientDTO updatedPatient = patientAppService.updatePatient(user, idPatient, patientDTO);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient mis à jour avec succès",
                updatedPatient
        );
        return ResponseEntity.ok(response);
    }

    //supp
    @DeleteMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(
        @PathVariable int idPatient , 
         Authentication auth ) {

                
        UserDTO user = authService.getUserDto(auth) ;
        patientAppService.deletePatient(  user , idPatient);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient supprimé avec succès",
                null
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Créer un dossier médical (nécessite permission CREE_DOSSIER_MEDICAL)
     */
    @PostMapping("/{idPatient}/dossier")
    public ResponseEntity<ApiResponse<DossierMedicalDTO>> associateDossierMedical(
             
            @PathVariable int idPatient,
            @RequestBody DossierMedicalDTO dossierDTO , 
         Authentication auth ) {


        UserDTO user = authService.getUserDto(auth) ;
        DossierMedicalDTO dossier = patientAppService.associateDossierMedical(user, idPatient, dossierDTO);
        ApiResponse<DossierMedicalDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Dossier médical créé avec succès",
                dossier
        );
        return ResponseEntity. status(HttpStatus.CREATED).body(response);
    }

    /**
     * Mettre à jour un dossier médical (nécessite permission MODIFIER_DOSSIER_MEDICAL)
     */
    @PutMapping("/{idPatient}/dossier")
    public ResponseEntity<ApiResponse<DossierMedicalDTO>> updateDossierMedical(
            @PathVariable int idPatient,
            @RequestBody DossierMedicalDTO dossierDTO ,
        Authentication auth ) {

                
        UserDTO user = authService.getUserDto(auth) ;

        DossierMedicalDTO dossier = patientAppService. updateDossierMedical(user, idPatient, dossierDTO);
        ApiResponse<DossierMedicalDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Dossier médical mis à jour avec succès",
                dossier
        );
        return ResponseEntity.ok(response);
    }

    // ============ OPÉRATIONS DE CONSULTATION (SANS PERMISSIONS) ============

    /**
     * Récupérer un patient par son ID
     */
    @GetMapping("/{idPatient}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(@PathVariable int idPatient , Authentication authentication) {

        UserDTO user = authService.getUserDto(authentication) ;
        PatientDTO patient = patientAppService.getPatientById(idPatient , user );
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient récupéré avec succès",
                patient
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer tous les patients d'un cabinet
     */
    @GetMapping("/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getAllPatientsByCabinet(@PathVariable int idCabinet  , Authentication authentication) {

        UserDTO user = authService.getUserDto(authentication) ;
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
    public ResponseEntity<ApiResponse<List<PatientDTO>>> searchPatientsByNom(
            @RequestParam String nom,
            @RequestParam int idCabinet) {

        List<PatientDTO> patients = patientAppService.getPatientsByNomAndCabinet(nom, idCabinet);
        ApiResponse<List<PatientDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patients trouvés avec succès",
                patients
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Rechercher un patient par CIN
     */
    @GetMapping("/cin/{cin}/cabinet/{idCabinet}")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientByCin(
            @PathVariable String cin,
            @PathVariable int idCabinet) {

        PatientDTO patient = patientAppService.getPatientByCinAndCabinet(cin, idCabinet);
        ApiResponse<PatientDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Patient trouvé avec succès",
                patient
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
