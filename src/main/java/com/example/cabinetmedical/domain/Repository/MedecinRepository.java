package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

public interface MedecinRepository {
    public MedecinEntity find(int id);
}
