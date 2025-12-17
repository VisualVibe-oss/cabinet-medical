package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;

public interface SecretaireRepository {
    public Secretaire findById(int id);
}
