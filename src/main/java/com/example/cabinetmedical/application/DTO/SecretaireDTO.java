package com.example.cabinetmedical.application.dto;
import com.example.cabinetmedical.application.dto.UserDTO;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SecretaireDTO  extends UserDTO{
    private int idSecretaire;
    private String nom;
    private String prenom;
    private Float salaire;
    private String telephone;
    private String motdePasse ; 
    private Set<PermissionKey> permissionKeys = new HashSet<>();


}
