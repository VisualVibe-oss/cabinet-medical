package com.example.cabinetmedical.domain.model.secretaire;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Employee.Employee;
import com.example.cabinetmedical.domain.utils.PermissionKey;

import java.util.Set;

public class Secretaire extends Employee {
    public Secretaire(int idSecretaire, String nom, String prenom, String email, String password, Float salaire, String telephone, Cabinet cabinet, Set<PermissionKey> PermitionKeys) {
        super(idSecretaire, nom, prenom, email, password, salaire, telephone, cabinet, PermitionKeys);
    }
    public Secretaire(){}
}
