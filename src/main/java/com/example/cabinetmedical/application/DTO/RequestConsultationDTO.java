

package com.example.cabinetmedical.application.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RequestConsultationDTO {
    private String motif;
    private String symptome;
    private Double temperature;
    private String dateSymptome;
    private Double poid;
    private Double tension;
    private Integer frequenceCardiaque;
    private String observationClinique;
    
    private List<ExamenDemandeDTO> examenDemande;
    private OrdonnanceDTO ordonnance;
}



