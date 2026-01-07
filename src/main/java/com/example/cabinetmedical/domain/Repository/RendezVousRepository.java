package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

import java.util.List;

public interface RendezVousRepository {
    RendezVousEntity save(RendezVousEntity rve);
    List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet);
    RendezVousEntity findById(int idRendezVous);
}
