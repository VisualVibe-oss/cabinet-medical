package com.example.cabinetmedical.domain.model.ordonnance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentOrdonnance {
    private int idMedciamentOrdonnance;
    private String nomMedicament; // On peut simplifier le domaine en ne gardant que le nom ou l'objet complet
    private String duration;
    private String dosage;

    public MedicamentOrdonnance() {}

    // Getters et Setters...
}