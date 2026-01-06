package com.example.cabinetmedical.infrastructure.repository.medicament;

import com.example.cabinetmedical.infrastructure.entity.MedicamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MedicamentRepository extends JpaRepository<MedicamentEntity, Integer> {
    Optional<MedicamentEntity> findByNom(String nom);

    boolean existsByNom(String nom);
}
