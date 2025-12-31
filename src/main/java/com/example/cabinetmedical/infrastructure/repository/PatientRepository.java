package com.example.cabinetmedical.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cabinetmedical.infrastructure.entity.PatientEntity;


@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Number>  {

    
} 
