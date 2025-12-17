package com.example.cabinetmedical.infrastructure.repository.RendezVous;


import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringRendezVousRepository extends JpaRepository<RendezVousEntity,Integer> {
}
