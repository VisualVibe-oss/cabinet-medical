package com.example.cabinetmedical.domain.service.cabinetStats;

import com.example.cabinetmedical.domain.model.cabinetStats.CabinetStats;
import org.springframework.stereotype.Service;

@Service
public class CabinetStatsDomainService {

    public void validate(CabinetStats cabinetStatistiques) {
        if(cabinetStatistiques.getCabinetPro()==null ||cabinetStatistiques.getCabinetBasic()==null
                ||cabinetStatistiques.getCabinetTotale()==null){
            throw new IllegalArgumentException("Stats vides");
        }
        if(cabinetStatistiques.getCabinetPro()<0 ||cabinetStatistiques.getCabinetBasic()<0
            ||cabinetStatistiques.getCabinetTotale()<0){
            throw new IllegalArgumentException("Nombres negatifes");
        }
    }
}
