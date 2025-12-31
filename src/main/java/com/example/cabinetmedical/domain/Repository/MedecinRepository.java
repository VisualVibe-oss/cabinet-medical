package com.example.cabinetmedical.domain.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

import java.util.List;
import java.util.Optional;

public interface MedecinRepository {
    MedecinEntity find(int id);
    List<MedecinEntity> findAll();
    Optional<MedecinEntity> findByEmail(String email);

    // Cette méthode va chercher le cabinet lié au secrétaire par son ID
    @Query("SELECT c.cabinet FROM MedecinEntity c WHERE c.idMedecin = :id")
    CabinetEntity findCabinetByMedecin(@Param("id") int idMedecin);
}
