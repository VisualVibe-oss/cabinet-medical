package com.example.cabinetmedical.infrastructure.repository.RendezVous;


import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringRendezVousRepository extends JpaRepository<RendezVousEntity,Integer> {
    List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet);
}
