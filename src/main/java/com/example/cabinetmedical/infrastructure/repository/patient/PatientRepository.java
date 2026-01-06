package com.example.cabinetmedical.infrastructure.repository.patient;

import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,Integer> {
    Optional<PatientEntity> findByCinAndCabinet_IdCabinet(String cin, int idCabinet);

    boolean existsByCinAndCabinet_IdCabinet(String cin, int idCabinet);

    @Query("SELECT p from PatientEntity p where p.cabinet.idCabinet = :idCabinet")
    List<PatientEntity> findByIdCabinet(@Param("idCabinet") int idCabinet);

    List<PatientEntity> findByNomAndCabinet_IdCabinet(String nom, int idCabinet);

}
