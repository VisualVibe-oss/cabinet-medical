package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

import java.util.List;

public interface MedecinRepository {
    MedecinEntity find(int id);
    List<MedecinEntity> findAll();
}
