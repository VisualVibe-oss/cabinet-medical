package com.example.cabinetmedical.infrastructure.mapper.cabinetEtat;

import com.example.cabinetmedical.application.dto.cabinetEtat.CabinetEtatDTO;
import com.example.cabinetmedical.domain.model.cabinetEtat.CabinetEtat;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.stereotype.Component;

@Component
public class CabinetEtatMapper {
    //dto to domain
    public CabinetEtat toDomain(CabinetEtatDTO dto){
        if(dto == null){
            return null;
        }
        return new CabinetEtat(dto.getEstActif());
    }

    //enity to domain
    public CabinetEtat toDomain(CabinetEntity entity){
        if(entity == null){return null;}
        return new CabinetEtat(entity.getEtat());
    }
    //entity to dto
    public static CabinetEtatDTO toDTO(CabinetEntity entity){
        if(entity == null){return null;}
        return  CabinetEtatDTO.builder()
                .id(entity.getIdCabinet())
                .nom(entity.getNom())
                .specialite(entity.getSpecialite())
                .adresse(entity.getAdresse())
    
                .estActif(entity.getEtat())
                .build();
    }

    // Méthode pour mettre à jour l'entity depuis le domain
    public void updateEntity(CabinetEtat domain, CabinetEntity entity){
        if(domain == null || entity == null){
            return;
        }
        entity.setEtat(domain.getEtat());
    }
}
