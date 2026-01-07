package com.example.cabinetmedical.infrastructure.repository.RendezVous;

import com.example.cabinetmedical.domain.utils.RendezVousState;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface SpringRendezVousRepository extends JpaRepository<RendezVousEntity, Integer> {

    List<RendezVousEntity> findByCabinet_IdCabinetAndDateDebutRendezVous(int idCabinet, LocalDateTime date);

    @Modifying
    @Transactional
    @Query("UPDATE RendezVousEntity r SET r.statut = :#{#rendezVous.statut} WHERE r.idRendezVous = :#{#rendezVous.idRendezVous}")
    int setSateRendezVous(@Param("rendezVous") RendezVousEntity rendezVous);

  

    RendezVousEntity findByIdRendezVous(int idRendezVous);
   // Méthode personnalisée pour récupérer le Patient via l'ID du RendezVous
    @Query("SELECT r.patient FROM RendezVousEntity r WHERE r.idRendezVous = :id")
    Optional<PatientEntity> findPatientByIdRendezVous(@Param("id") int idRendezVous);

    List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet);


    RendezVousEntity findFirstByPatient_IdPatientOrderByDateDebutRendezVousDesc(int idPatient);

    List<RendezVousEntity> findByPatient_IdPatient(int idPatient);

    @Query("SELECT r FROM RendezVousEntity r WHERE DATE(r.dateRendezVous) = DATE(:date) AND r.cabinet.idCabinet = :idCabinet")
    List<RendezVousEntity> findByDateAndCabinet(@Param("date") LocalDate date, @Param("idCabinet") int idCabinet);

    @Query("SELECT r FROM RendezVousEntity r WHERE r. dateRendezVous BETWEEN :dateDebut AND :dateFin AND r.cabinet.idCabinet = :idCabinet")
    List<RendezVousEntity> findByDateRangeAndCabinet(
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin,
            @Param("idCabinet") int idCabinet);

    List<RendezVousEntity> findByStatutAndCabinet_IdCabinet(String statut, int idCabinet);


    @Query("SELECT COUNT(r) > 0 FROM RendezVousEntity r WHERE DATE(r.dateRendezVous) = DATE(:date) AND r.cabinet.idCabinet = :idCabinet")
    boolean existsByDateAndCabinet(@Param("date") LocalDate date, @Param("idCabinet") int idCabinet);

}