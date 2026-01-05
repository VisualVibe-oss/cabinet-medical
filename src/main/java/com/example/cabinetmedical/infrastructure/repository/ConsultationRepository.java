package com.example.cabinetmedical.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;

@Repository
public interface  ConsultationRepository  extends JpaRepository<ConsultationEntity, Number>{
    @Query("""
        SELECT c
        FROM ConsultationEntity c
        WHERE c.rendezVous.patient.idPatient = :patientId
        ORDER BY c.date DESC
    """)
    List<ConsultationEntity> findAllByPatientId(@Param("patientId") int patientId);
}
