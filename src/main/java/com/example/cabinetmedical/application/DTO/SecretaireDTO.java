package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import lombok.Data;

@Data
public class SecretaireDTO {
    private int idSecretaire;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Float salaire;
    private String telephone;
    private Cabinet cabinet;

    public SecretaireDTO(int idSecretaire, String nom, String prenom, String email, String password, Float salaire, String telephone, Cabinet cabinet) {
        this.idSecretaire = idSecretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.salaire = salaire;
        this.telephone = telephone;
        this.cabinet = cabinet;
    }
}
