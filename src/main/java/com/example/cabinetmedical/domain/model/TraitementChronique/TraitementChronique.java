package com.example.cabinetmedical.domain.model.TraitementChronique;

import java.util.Date;

public class TraitementChronique {
    private int id;
    private int dossierId;
    private String medicament;
    private String dosage;
    private String frequence;
    private Date dateDebut;

    public TraitementChronique() {
    }

    public TraitementChronique(int id, int dossierId, String medicament, String dosage, String frequence, Date dateDebut) {
        this.id = id;
        this.dossierId = dossierId;
        this.medicament = medicament;
        this.dosage = dosage;
        this.frequence = frequence;
        this.dateDebut = dateDebut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
