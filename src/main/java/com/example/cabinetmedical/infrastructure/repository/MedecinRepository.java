package com.example.cabinetmedical.infrastructure.repository;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<MedecinEntity,Integer> {
    Optional<MedecinEntity> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByTelephone(String telephone);

    boolean existsByEmailAndPassword(String email , String password) ;


    @Query("SELECT c.cabinet FROM MedecinEntity c WHERE c.idMedecin = :id")
    CabinetEntity findCabinetByMedecin(@Param("id") int idMedecin);

    @Query("SELECT s.cabinet FROM MedecinEntity s WHERE s.email = :email")
    CabinetEntity findCabinetByMedecinEmail(String email);

}
