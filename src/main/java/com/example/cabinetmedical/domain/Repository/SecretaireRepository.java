package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;

import java.util.List;

public interface SecretaireRepository {
    SecretaireEntity findById(int id);
    SecretaireEntity save(SecretaireEntity se);
    List<SecretaireEntity> findByidCabinet(int cabinetId);
    void deleteByidSecretaire(int idsecretaire);
    List<SecretaireEntity> findAll();


}
