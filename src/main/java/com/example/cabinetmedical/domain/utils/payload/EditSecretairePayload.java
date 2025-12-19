package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.PermissionKey;

import java.util.Set;

public class EditSecretairePayload {
    private final Secretaire secretaire;

    private final String nom;
    private final String prenom;
    private final String email;
    private final String telephone;
    private final Float salaire;

    private final Set<PermissionKey> Permissions;

    public EditSecretairePayload(
            Secretaire secretaire,
            String nom,
            String prenom,
            String email,
            String telephone,
            Float salaire,
            Set<PermissionKey> Permissions
    ) {
        this.secretaire = secretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.salaire = salaire;
        this.Permissions = Permissions;
    }

    public Secretaire getSecretaire() { return secretaire; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public Float getSalaire() { return salaire; }
    public Set<PermissionKey> getPermissions() { return Permissions; }
}
