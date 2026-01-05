package com.example.cabinetmedical.infrastructure.repository.RendezVous;

import com.example.cabinetmedical.domain.utils.RendezVousState;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpringRendezVousRepository extends JpaRepository<RendezVousEntity, Integer> {

    List<RendezVousEntity> findByCabinet_IdCabinetAndDateRendezVousAfter(int idCabinet, Date date);

    @Modifying
    @Transactional
    @Query("UPDATE RendezVousEntity r SET r.statut = :#{#rendezVous.statut} WHERE r.idRendezVous = :#{#rendezVous.idRendezVous}")
    int setSateRendezVous(@Param("rendezVous") RendezVousEntity rendezVous);

    RendezVousEntity findByPatient_IdPatient(int idPatient);

    RendezVousEntity findByIdRendezVous(int idRendezVous);
   // Méthode personnalisée pour récupérer le Patient via l'ID du RendezVous
    @Query("SELECT r.patient FROM RendezVousEntity r WHERE r.idRendezVous = :id")
    Optional<PatientEntity> findPatientByIdRendezVous(@Param("id") int idRendezVous);

    List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet);
}