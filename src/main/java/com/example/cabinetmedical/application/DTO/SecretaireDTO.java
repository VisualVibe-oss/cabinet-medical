package com.example.cabinetmedical.application.dto;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
    private Set<PermissionKey> permissionKeys = new HashSet<>();

    public SecretaireDTO(int idSecretaire ,String nom, String prenom, String email, String password, Float salaire, String telephone, Cabinet cabinet, Set<PermissionKey> permissionKeys) {
        this.idSecretaire = idSecretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.salaire = salaire;
        this.telephone = telephone;
        this.cabinet = cabinet;
        this.permissionKeys = permissionKeys;
    }
}
