package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;

public interface SecretaireRepository {
    public SecretaireEntity findById(int id);
    public SecretaireEntity save(SecretaireEntity se);
}
