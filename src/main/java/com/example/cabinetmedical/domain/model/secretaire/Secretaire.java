package com.example.cabinetmedical.domain.model.secretaire;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Employee.Employee;
import com.example.cabinetmedical.domain.model.Employee.SecretaryPermissions;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

import java.util.Set;

public class Secretaire extends Employee {

    public Secretaire(int idSecretaire, String nom, String prenom, String email,
                      String password, Float salaire, String telephone,
                      Cabinet cabinet, Set<PermissionKey> permissionKeys) {
        super(idSecretaire, nom, prenom, email, password, salaire, telephone, cabinet, permissionKeys);
    }

    public Secretaire() {
        super();
    }
}
