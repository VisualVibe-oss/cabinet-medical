package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medciament_ordonnance")
public class MedciamentOrdonnanceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedciamentOrdonnance;

    //! Temporaire pour le test 
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idMedicament")
    private MedicamentEntity medicament;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idOrdonnanceMed")
    private OrdonnanceMedEntity ordonnanceMed;

    @Column(nullable = true)
    private String duration ; 

    @Column(nullable = true)
    private String Dosage ;


    public MedciamentOrdonnanceEntity(MedicamentEntity medicament, OrdonnanceMedEntity ordonnanceMed, String duration, String dosage) {
        this.medicament = medicament;
        this.ordonnanceMed = ordonnanceMed;
        this.duration = duration;
        this.Dosage = dosage;
    }

    public MedciamentOrdonnanceEntity() {
    }

    public int getIdMedciamentOrdonnance() {
        return idMedciamentOrdonnance;
    }
    public void setIdMedciamentOrdonnance(int idMedciamentOrdonnance) {
        this.idMedciamentOrdonnance = idMedciamentOrdonnance;
    }
   public void setDosage(String dosage) {
        Dosage = dosage;
    } 
    public String getDosage() {
        return Dosage;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDuration() {
        return duration;
    }
    public MedicamentEntity getMedicament() {
        return medicament;
    }
    public void setMedicament(MedicamentEntity medicament) {
        this.medicament = medicament;
    }
    public OrdonnanceMedEntity getOrdonnanceMed() {
        return ordonnanceMed;
    }
    public void setOrdonnanceMed(OrdonnanceMedEntity ordonnanceMed) {
        this.ordonnanceMed = ordonnanceMed;
    }




}
