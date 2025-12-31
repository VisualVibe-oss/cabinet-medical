package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.utils.OfferType;
import com.example.cabinetmedical.infrastructure.entity.OffreEntity;

public class OffreMapper {

    public static Offre entityToDomain(OffreEntity entity) {
        if (entity == null) {
            return null;
        }

        Offre domain = new Offre();
        
        // Mappage des clés et types
        domain.setPackKey(entity.getKey());
        
        // Conversion String -> Enum pour OfferType
        if (entity.getType() != null) {
            try {
                domain.setType(OfferType.valueOf(entity.getType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Gérer le cas où la string en BD ne correspond pas à l'Enum
                domain.setType(null); 
            }
        }

        // Mappage des attributs ajoutés
        domain.setPrix(entity.getPrix());
        domain.setDescription(entity.getDescription());
        domain.setOffreDurationInDays(entity.getOffreDurationInDays());

        return domain;
    }

    public static OffreEntity toEntity(Offre domain) {
        if (domain == null) {
            return null;
        }

        OffreEntity entity = new OffreEntity();
        entity.setKey(domain.getPackKey());
        
        if (domain.getType() != null) {
            entity.setType(domain.getType().name());
        }
        
        entity.setPrix(domain.getPrix());
        entity.setDescription(domain.getDescription());
        entity.setOffreDurationInDays(domain.getOffreDurationInDays());
        
        return entity;
    }
}