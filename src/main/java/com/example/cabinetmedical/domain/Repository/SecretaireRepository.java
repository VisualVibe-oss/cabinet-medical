package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;

import java.util.List;
import java.util.Optional;

public interface SecretaireRepository {
    Optional<SecretaireEntity> findById(int id);
    SecretaireEntity save(SecretaireEntity se);
    List<SecretaireEntity> findByidCabinet(int cabinetId);
    void delete(SecretaireEntity se);
    List<SecretaireEntity> findAll();


}
