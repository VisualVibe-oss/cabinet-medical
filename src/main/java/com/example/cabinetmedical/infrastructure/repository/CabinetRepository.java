package com.example.cabinetmedical.infrastructure.repository;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetRepository extends JpaRepository<CabinetEntity, Integer> {
}