package com.example.cabinetmedical.application.DTO;

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
    private int idCabinet;
    private Set<PermissionKey> permissionKeys = new HashSet<>();


}
