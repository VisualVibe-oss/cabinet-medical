package com.example.cabinetmedical.infrastructure.repository.rendezVous;

import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVousEntity, Integer> {
    List<RendezVousEntity> findByCabinet_IdCabinet(int idCabinet);

    List<RendezVousEntity> findByPatient_IdPatient(int idPatient);

    @Query("SELECT r FROM RendezVousEntity r WHERE DATE(r.dateRendezVous) = DATE(:date) AND r.cabinet.idCabinet = :idCabinet")
    List<RendezVousEntity> findByDateAndCabinet(@Param("date") Date date, @Param("idCabinet") int idCabinet);

    @Query("SELECT r FROM RendezVousEntity r WHERE r. dateRendezVous BETWEEN :dateDebut AND :dateFin AND r.cabinet.idCabinet = :idCabinet")
    List<RendezVousEntity> findByDateRangeAndCabinet(
            @Param("dateDebut") Date dateDebut,
            @Param("dateFin") Date dateFin,
            @Param("idCabinet") int idCabinet);

    List<RendezVousEntity> findByStatutAndCabinet_IdCabinet(String statut, int idCabinet);

    @Query("SELECT COUNT(r) > 0 FROM RendezVousEntity r WHERE DATE(r.dateRendezVous) = DATE(:date) AND r.cabinet.idCabinet = :idCabinet")
    boolean existsByDateAndCabinet(@Param("date") Date date, @Param("idCabinet") int idCabinet);
}
