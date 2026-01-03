package com.example.cabinetmedical.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;

@Repository
public interface  ConsultationRepository  extends JpaRepository<ConsultationEntity, Number>{
    
}
