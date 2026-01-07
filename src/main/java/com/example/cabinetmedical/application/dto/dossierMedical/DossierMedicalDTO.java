package com.example.cabinetmedical.application.dto.dossierMedical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.example.cabinetmedical.application.dto.patient.PatientDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DossierMedicalDTO {
    private Integer idDossier;
    private String antMedicaux;
    private PatientDTO patient ; 
    private Date dateCreation  ; 
}
