package com.example.cabinetmedical.infrastructure.repository.Facture;

import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpringFactureRepository extends JpaRepository<FactureEntity,Integer> {
    List<FactureEntity> findAllByCabinet_idCabinetAndDateBetween(int idCabinet, Date start, Date end);
    List<FactureEntity> findAllByCabinet_idCabinet(int idCabinet);
}
