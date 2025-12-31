package com.example.cabinetmedical.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.infrastructure.entity.OffreEntity;

public interface OffreRepository extends JpaRepository<OffreEntity, Integer> {
    Optional<OffreEntity> findByPackKey(PackKey key);
    
}
