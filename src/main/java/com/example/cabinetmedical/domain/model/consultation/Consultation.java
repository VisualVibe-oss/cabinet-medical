package com.example.cabinetmedical.domain.model.consultation;

import java.util.Date;
import java.util.List;

import com.example.cabinetmedical.domain.model.Examen.Examen;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.domain.model.ordonnance.OrdonnanceMed;
import com.example.cabinetmedical.domain.model.ordonnance.OrdonnanceSup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consultation {
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
    private String examenClinique;
    private String examenSupplementaire;
    private String diagnostic;
    private String observations;
    
    
    // Objets du domaine
    private List<Examen> examens;
    private OrdonnanceSup ordonnanceSup;
    private OrdonnanceMed ordonnanceMed;

    private Facture facture;
    
    // Note: Ajoutez RendezVous et DossierMedical ici si nécessaire

    public Consultation() {}

    // Getters et Setters...
}