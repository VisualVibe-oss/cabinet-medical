package com.example.cabinetmedical.infrastructure.mapper.cabinet;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import org.springframework.stereotype.Component;

@Component
public class CabinetMapper {

    /**
     * Entity → Domain
     * Convertit uniquement les données de base (pas les relations)
     */
    public Cabinet toDomain(CabinetEntity entity) {
        if (entity == null) {
            return null;
        }

        Cabinet domain = new Cabinet();
        domain.setIdCabinet(entity. getIdCabinet());
        domain.setLogo(entity. getLogo());
        domain.setNom(entity.getNom());
        domain.setSpecialite(entity.getSpecialite());
        domain.setAdresse(entity.getAdresse());
        domain.setTelephone(entity.getTelephone());
        domain.setEtat(entity.getEtat());

        // Note :  On ne mappe pas les relations ici pour éviter les boucles infinies
        // Les relations sont mappées au besoin dans les services

        return domain;
    }

    /**
     * Domain → Entity
     * Convertit uniquement les données de base
     */
    public CabinetEntity toEntity(Cabinet domain) {
        if (domain == null) {
            return null;
        }

        CabinetEntity entity = new CabinetEntity();
        entity.setIdCabinet(domain.getIdCabinet());
        entity.setLogo(domain.getLogo());
        entity.setNom(domain.getNom());
        entity.setSpecialite(domain.getSpecialite());
        entity.setAdresse(domain. getAdresse());
        entity.setTelephone(domain.getTelephone());
        entity.setEtat(domain.getEtat());

        return entity;
    }
}