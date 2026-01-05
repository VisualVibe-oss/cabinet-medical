package com.example.cabinetmedical.infrastructure.repository.Depence;

import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.infrastructure.entity.DepenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpringDepenceRepository extends JpaRepository<DepenceEntity,Integer> {
    List<DepenceEntity> findALLByCabinet_idCabinetAndDateBetween(int idCabinet, Date start, Date end);
    List<DepenceEntity> findAllByCabinet_idCabinet(int idCabinet);
    List<DepenceEntity> findAllByType(DepenceType depenceType);
    List<DepenceEntity> findAllByParent(DepenceEntity parent);
}
