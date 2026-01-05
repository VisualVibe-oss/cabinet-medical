
package com.example.cabinetmedical.application.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConsultationResponseDTO {

    private int idConsultation;
    private String type;
    private String motif;
    private String symptome;
    private Double temperature;
    private String dateSymptome;
    private Double poid;
    private Double tension;
    private Integer frequenceCardiaque;
    private String observationClinique;
    private Date date;

    // Ordonnance sup
    private String repos;

    // Ordonnance med
    private List<MedicamentResponseDTO> medicaments;

    // getters & setters
}