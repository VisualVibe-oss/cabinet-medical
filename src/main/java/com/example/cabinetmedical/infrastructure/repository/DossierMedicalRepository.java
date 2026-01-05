package com.example.cabinetmedical.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;

@Repository
public interface DossierMedicalRepository extends  JpaRepository<DossierMedicalEntity, Integer> {

    Optional<DossierMedicalEntity> findByPatient_IdPatient(int idPatient);
    
}