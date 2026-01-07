package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.PermissionKey;

import java.util.List;
import java.util.Set;

public class EditSecretairePayload {
    private final Secretaire secretaire;

    private final String nom;
    private final String prenom;
    private final String email;
    private final String telephone;
    private final Float salaire;
    List<String> existingSecretaireEmails;
    List<String> existingMedecinEmails;

    private final Set<PermissionKey> Permissions;

    public EditSecretairePayload(
            Secretaire secretaire,
            String nom,
            String prenom,
            String email,
            String telephone,
            Float salaire,
            List<String> existingSecretaireEmails,
            List<String> existingMedecinEmails,
            Set<PermissionKey> Permissions
    ) {
        this.secretaire = secretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.salaire = salaire;
        this.existingSecretaireEmails = existingSecretaireEmails;
        this.existingMedecinEmails = existingMedecinEmails;
        this.Permissions = Permissions;
    }

    public Secretaire getSecretaire() { return secretaire; }
    public String getNom() { return nom; }

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

    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public Float getSalaire() { return salaire; }
    public Set<PermissionKey> getPermissions() { return Permissions; }
}
