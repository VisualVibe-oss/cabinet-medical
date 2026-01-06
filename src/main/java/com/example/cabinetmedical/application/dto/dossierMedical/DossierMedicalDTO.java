package com.example.cabinetmedical.application.dto.dossierMedical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DossierMedicalDTO {
    private Integer idDossier;
    private String antMedicaux;
    private String antChirug;
    private String allergies;
    private String traitement;
    private String habitudes;
    private String historiqueConsultations;
    private String documentsMedicaux;
    private Date dateCreation;
}
