package com.example.cabinetmedical.domain.model.ordonnance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
public class OrdonnanceSup {
    private int idOrdonnanceSup;
    private String reposStrict;
    private String regime;
    private Boolean arretTravail;
    private String arretDebut;
    private String arretFin;


    // Getters et Setters...
}