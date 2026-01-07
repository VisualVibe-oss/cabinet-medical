package com.example.cabinetmedical.infrastructure.repository.cabinet;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

public interface CabinetRepository extends JpaRepository<CabinetEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE CabinetEntity c SET c.offre = null WHERE c.offre.idOffre = :idOffre")
    void nullifyOffreRelation(int idOffre);

    @Modifying
    @Transactional
    @Query("UPDATE CabinetEntity c SET c.dateFinOffre = null WHERE c.offre.idOffre = :idOffre")
    void setOffreEndDateToNullByOffreId(int idOffre);

    Page<CabinetEntity> findByDateFinOffreBefore(Date date, Pageable pageable);

    @Query("update CabinetEntity c set c.dateFinOffre  = :newDatefinoffre where c.idCabinet = :idCabinet")
    @Modifying
    @Transactional
    void updateDateFinOffreByIdCabinet(int idCabinet, Date newDatefinoffre);

    @Query("SELECT c.etat FROM CabinetEntity c WHERE c.medecin = :medecin")
    boolean findEtatByMedecin(MedecinEntity medecin);

    CabinetEntity findByIdCabinet(int idCabinet);


    List<CabinetEntity> findByEtatTrue();
    List<CabinetEntity> findByEtatFalse();

}