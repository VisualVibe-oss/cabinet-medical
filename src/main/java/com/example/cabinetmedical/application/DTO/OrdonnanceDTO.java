package com.example.cabinetmedical.application.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

public
@NoArgsConstructor
@Data
class OrdonnanceDTO {
    private List<MedicamentPrescritDTO> medicament;
    private String reposStrict;
    private String regime;
    private Boolean arretTravail;
    private String arretDebut;
    private String arretFin;
}
