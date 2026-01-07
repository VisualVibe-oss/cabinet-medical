package com.example.cabinetmedical.domain.model.AntecedantMedical;

import java.util.Date;

public class AntecedentMedical {
    private int id;
    private int dossierId;
    private String type;
    private String description;
    private Date dateDebut;

    // Constructeur par défaut
    public AntecedentMedical() {
    }

    // Constructeur avec tous les paramètres
    public AntecedentMedical(int id, int dossierId, String type, String description, Date dateDebut) {
        this.id = id;
        this.dossierId = dossierId;
        this.type = type;
        this.description = description;
        this.dateDebut = dateDebut;
    }

    // Getters et Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
