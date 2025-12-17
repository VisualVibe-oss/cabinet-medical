package com.example.cabinetmedical.domain.model.functionnalities.payload;

import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;

public class AddSecretairePayload {
    private Secretaire secretaire;
    private Medecin medecin;
    private int maxEmployees;

    public AddSecretairePayload(Secretaire secretaire, Medecin medecin, int maxEmployees) {
        this.secretaire = secretaire;
        this.medecin = medecin;
        this.maxEmployees = maxEmployees;
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
}
