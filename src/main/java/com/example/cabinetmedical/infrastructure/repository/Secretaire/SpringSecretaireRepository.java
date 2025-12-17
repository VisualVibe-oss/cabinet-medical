package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.domain.Repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringSecretaireRepository extends JpaRepository<SecretaireEntity,Integer> {

}
