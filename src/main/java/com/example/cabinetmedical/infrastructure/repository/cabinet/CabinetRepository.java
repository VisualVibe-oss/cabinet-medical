package com.example.cabinetmedical.infrastructure.repository.cabinet;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinetRepository extends JpaRepository<CabinetEntity, Integer> {
    List<CabinetEntity> findByEtatTrue();
    List<CabinetEntity> findByEtatFalse();
}