package com.example.cabinetmedical.domain.Repository;

import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.infrastructure.entity.DepenceEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DepenceRepository {
    List<DepenceEntity> findbyidCabinetAndDateBetween(int idCabinet, Date start, Date end);
    List<DepenceEntity> findallbyidCabinet(int idCabinet);
    List<DepenceEntity> findByType(DepenceType depenceType);
    DepenceEntity save(DepenceEntity depenceEntity);
    void delete(DepenceEntity depenceEntity);
    List<DepenceEntity> findAllByParent(DepenceEntity parent);
    Optional<DepenceEntity> findById(int id);
}
