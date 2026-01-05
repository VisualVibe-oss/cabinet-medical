package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;

import java.util.List;

public class AddSecretairePayload {
    private Secretaire secretaire;
    private Medecin medecin;
    private int maxEmployees;
    List<String> existingSecretaireEmails;
    List<String> existingMedecinEmails;

    public AddSecretairePayload(Secretaire secretaire, Medecin medecin, int maxEmployees,  List<String> existingSecretaireEmails, List<String> existingMedecinEmails) {
        this.secretaire = secretaire;
        this.medecin = medecin;
        this.maxEmployees = maxEmployees;
        this.existingSecretaireEmails = existingSecretaireEmails;
        this.existingMedecinEmails = existingMedecinEmails;
    }
    public AddSecretairePayload() {}

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public List<String> getExistingSecretaireEmails() {
        return existingSecretaireEmails;
    }

    public void setExistingSecretaireEmails(List<String> existingSecretaireEmails) {
        this.existingSecretaireEmails = existingSecretaireEmails;
    }

    public List<String> getExistingMedecinEmails() {
        return existingMedecinEmails;
    }

    public void setExistingMedecinEmails(List<String> existingMedecinEmails) {
        this.existingMedecinEmails = existingMedecinEmails;
    }
}

