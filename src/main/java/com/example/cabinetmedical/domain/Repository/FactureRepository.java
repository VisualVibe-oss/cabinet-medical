package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.infrastructure.entity.FactureEntity;

import java.util.Date;
import java.util.List;

public interface FactureRepository {
    List<FactureEntity> findbyidCabinetAndDateBetween(int idCabinet, Date start, Date end);
    List<FactureEntity> findallbyidCabinet(int idCabinet);
    FactureEntity save(FactureEntity factureEntity);
}
