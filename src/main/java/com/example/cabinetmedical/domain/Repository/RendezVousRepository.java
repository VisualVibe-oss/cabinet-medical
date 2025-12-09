package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;

public interface RendezVousRepository {
    RendezVousEntity save(RendezVousEntity rve);
}
