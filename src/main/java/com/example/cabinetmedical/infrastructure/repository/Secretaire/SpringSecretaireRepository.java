package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;

@Repository
public interface SpringSecretaireRepository extends JpaRepository<SecretaireEntity, Integer> {
        @Query("SELECT s.cabinet FROM SecretaireEntity s WHERE s.idSecretaire = :id")
<<<<<<< HEAD
         public CabinetEntity findCabinetBySecretaireId(int id) ;    List<SecretaireEntity> findByCabinet_idCabinet(int idcabinet);
    void deleteByidSecretaire(int idsecretaire);
=======
        public CabinetEntity findCabinetBySecretaireId(int id);
>>>>>>> acf2c00 (feat: Add consultation creation functionality and related DTOs)

        @Query("SELECT s.cabinet FROM SecretaireEntity s WHERE s.email = :email")
        CabinetEntity findCabinetBySecretaireEmail(String email);

        @Query("SELECT s.email FROM SecretaireEntity s WHERE s.cabinet.idCabinet = :idCabinet")
        List<String> findEmailsByCabinetId(@Param("idCabinet") int idCabinet);
}
