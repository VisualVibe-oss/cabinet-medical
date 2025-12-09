package com.example.cabinetmedical.infrastructure.repository;

import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<MedecinEntity,Integer> {
    Optional<MedecinEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
