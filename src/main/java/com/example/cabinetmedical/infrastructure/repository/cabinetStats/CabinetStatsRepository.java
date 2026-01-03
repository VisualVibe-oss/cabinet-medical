package com.example.cabinetmedical.infrastructure.repository.cabinetStats;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetStatsRepository extends JpaRepository<CabinetEntity, Integer> {

    @Query("SELECT COUNT(c) FROM CabinetEntity c")
    Integer countCabinets();

    @Query("SELECT COUNT(c) FROM CabinetEntity c " +
            "JOIN c.offre o " +
            "WHERE o.type = 'BASIC'")
    Integer countCabinetsBasic();

    @Query("SELECT COUNT(c) FROM CabinetEntity c " +
            "JOIN c.offre o " +
            "WHERE o.type = 'PRO'")
    Integer countCabinetPro();

}
