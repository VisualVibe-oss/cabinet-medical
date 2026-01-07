package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RendezVousRepository {
    RendezVousEntity save(RendezVousEntity rve);
    List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet);
    RendezVousEntity findById(int idRendezVous);
}
