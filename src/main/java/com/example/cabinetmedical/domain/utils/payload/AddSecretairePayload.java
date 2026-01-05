package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;

import java.util.List;

public class AddSecretairePayload {
    private Secretaire secretaire;
    private List<Secretaire> total;
    private int maxEmployees;
    List<String> existingSecretaireEmails;
    List<String> existingMedecinEmails;

    public AddSecretairePayload(Secretaire secretaire, List<Secretaire> total, int maxEmployees,  List<String> existingSecretaireEmails, List<String> existingMedecinEmails) {
        this.secretaire = secretaire;
        this.total = total;
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


    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public List<Secretaire> getTotal() {
        return total;
    }

    public void setTotal(List<Secretaire> total) {
        this.total = total;
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

