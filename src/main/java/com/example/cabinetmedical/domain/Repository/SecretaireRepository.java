package com.example.cabinetmedical.domain.Repository;


import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;

import java.util.List;
import java.util.Optional;

public interface SecretaireRepository {
    Optional<SecretaireEntity> findById(int id);
    SecretaireEntity save(SecretaireEntity se);

    // Cette méthode va chercher le cabinet lié au secrétaire par son ID

     public CabinetEntity findCabinetBySecretaireId(int idSecretaire) ;

    
    List<SecretaireEntity> findByidCabinet(int cabinetId);
    void delete(SecretaireEntity se);
    List<SecretaireEntity> findAll();


}
