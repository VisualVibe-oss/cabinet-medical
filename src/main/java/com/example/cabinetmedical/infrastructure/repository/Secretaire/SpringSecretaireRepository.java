package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Repository
public interface SpringSecretaireRepository extends JpaRepository<SecretaireEntity, Integer> {

        
        @Query("SELECT s.cabinet FROM SecretaireEntity s WHERE s.idSecretaire = :id")
        public CabinetEntity findCabinetBySecretaireId(int id);

        List<SecretaireEntity> findByCabinet_idCabinet(int idcabinet);

        void deleteByidSecretaire(int idsecretaire);

        @Query("SELECT s.cabinet FROM SecretaireEntity s WHERE s.email = :email")
        CabinetEntity findCabinetBySecretaireEmail(String email);

        @Query("SELECT s.email FROM SecretaireEntity s WHERE s.cabinet.idCabinet = :idCabinet")
        List<String> findEmailsByCabinetId(@Param("idCabinet") int idCabinet);

        @Query("SELECT s.cabinet.etat FROM SecretaireEntity s WHERE s.email = :email")
        boolean findEtatBySecretaireEmail(@Param("email") String email);

        Optional<SecretaireEntity> findByEmail(String email) ;

}
