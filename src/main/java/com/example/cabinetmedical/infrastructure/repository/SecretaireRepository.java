package com.example.cabinetmedical.infrastructure.repository;

import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretaireRepository extends JpaRepository<SecretaireEntity,Integer> {
    SecretaireEntity findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email , String password) ;

    
}
