package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringSecretaireRepository extends JpaRepository<SecretaireEntity,Integer> {
        @Query("SELECT s.cabinet FROM SecretaireEntity s WHERE s.idSecretaire = :id")
         public CabinetEntity findCabinetBySecretaireId(int id) ;    List<SecretaireEntity> findByCabinet_idCabinet(int idcabinet);
    void deleteByidSecretaire(int idsecretaire);
}
