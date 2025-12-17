package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;

public class DeleteSecretairePayload {
    Secretaire secretaire;
    Cabinet cabinet;

    public DeleteSecretairePayload(Secretaire secretaire, Cabinet cabinet) {
        this.secretaire = secretaire;
        this.cabinet = cabinet;
    }

    public Secretaire getSecretaire() {

        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
}
