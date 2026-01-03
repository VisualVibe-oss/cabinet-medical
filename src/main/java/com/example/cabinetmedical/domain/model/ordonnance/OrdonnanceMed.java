package com.example.cabinetmedical.domain.model.ordonnance;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class OrdonnanceMed {
    private int idOrdonnanceMed;
    private List<MedicamentOrdonnance> medicaments;

    public OrdonnanceMed() {}

    // Getters et Setters...
}