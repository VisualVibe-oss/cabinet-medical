package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.proprietaire.Propietaire;

public class Medecin extends Propietaire {
    public Medecin(int idMedecin, String nom, String prenom, String email, String password, String telephone, Cabinet cabinet) {
        super(idMedecin,nom, prenom, email, password, telephone, cabinet);
    }
}
