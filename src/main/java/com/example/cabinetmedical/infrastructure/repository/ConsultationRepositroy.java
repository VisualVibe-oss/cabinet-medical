package com.example.cabinetmedical.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

public interface ConsultationRepositroy extends JpaRepository<ConsultationEntity,Integer> {
    
}
