package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringSecretaireRepository extends JpaRepository<SecretaireEntity,Integer> {
    List<SecretaireEntity> findByCabinet_idCabinet(int idcabinet);
    void deleteByidSecretaire(int idsecretaire);
}
