package com.example.cabinetmedical.domain.model.secretaire;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Employee.Employee;

public class Secretaire extends Employee {
    public Secretaire(int idSecretaire, String nom, String prenom, String email, String password, Float salaire, String telephone, Cabinet cabinet) {
        super(idSecretaire, nom, prenom, email, password, salaire, telephone, cabinet);
    }
}
