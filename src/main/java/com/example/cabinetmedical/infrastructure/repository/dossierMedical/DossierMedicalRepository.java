package com.example.cabinetmedical.infrastructure.repository.dossierMedical;

import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DossierMedicalRepository extends JpaRepository<DossierMedicalEntity,Integer> {
    Optional<DossierMedicalEntity> findByPatient_IdPatient(Integer integer);

    boolean existsByPatient_IdPatient(Integer integer);
}
