package com.example.cabinetmedical.infrastructure.repository.Medecin;

import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringMedecinRepository extends JpaRepository<MedecinEntity,Integer> {
}
