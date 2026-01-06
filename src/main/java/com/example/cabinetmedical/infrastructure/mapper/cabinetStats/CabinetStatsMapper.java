package com.example.cabinetmedical.infrastructure.mapper.cabinetStats;

import com.example.cabinetmedical.application.dto.cabinetStats.CabinetStatsDTO;
import com.example.cabinetmedical.domain.model.cabinetStats.CabinetStats;
import org.springframework.stereotype.Component;

@Component
public class CabinetStatsMapper {
    //dto to domain
    public CabinetStats toDomain(CabinetStatsDTO cab){
        if(cab == null){
            return null;
        }
        return new CabinetStats(cab.getCabinetTotale(), cab.getCabinetBasic(), cab.getCabinetPro());
    }
    //domain to dto
    public CabinetStatsDTO toDTO(CabinetStats cab){
        if(cab == null){
            return null;
        }
        return new CabinetStatsDTO(cab.getCabinetTotale(), cab.getCabinetBasic(),  cab.getCabinetPro());
    }
}
