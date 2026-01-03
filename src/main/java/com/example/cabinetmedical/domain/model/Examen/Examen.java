package com.example.cabinetmedical.domain.model.Examen;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class Examen {
    private int idExamen;
    private String type;
    private String etat;
    private String file;

    public Examen() {}

   
    // Getters et Setters...
}