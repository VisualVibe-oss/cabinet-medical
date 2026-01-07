package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.Allergie.Allergie;
import com.example.cabinetmedical.infrastructure.entity.AllergieEntity;
import com.example.cabinetmedical.application.DTO.AllergieDTO;
import org.springframework.stereotype.Component;

@Component
public class AllergieMapper {

    
    public static Allergie toDomain(AllergieEntity entity) {
        if (entity == null) {
            return null;
        }

        Allergie domain = new Allergie();
        domain.setId(entity.getId());
        domain.setDossierId(entity.getDossierMedical() != null ?
                entity.getDossierMedical().getIdDossier() : 0);
        domain.setSubstance(entity.getSubstance());
        domain.setType(entity.getType());
        domain.setGravite(entity.getGravite());
        domain.setReaction(entity.getReaction());

        return domain;
    }

    /**
     * Convertit un modèle de domaine en Entity
     */
    public static AllergieEntity toEntity(Allergie domain) {
        if (domain == null) {
            return null;
        }

        AllergieEntity entity = new AllergieEntity();
        entity.setId(domain.getId());
        // Note: le DossierMedicalEntity doit être défini séparément
        entity.setSubstance(domain.getSubstance());
        entity.setType(domain.getType());
        entity.setGravite(domain.getGravite());
        entity.setReaction(domain.getReaction());

        return entity;
    }

    /**
     * Convertit un modèle de domaine en DTO
     */
    public static AllergieDTO toDto(Allergie domain) {
        if (domain == null) {
            return null;
        }

        AllergieDTO dto = new AllergieDTO();
        dto.setId(domain.getId());
        dto.setSubstance(domain.getSubstance());
        dto.setType(domain.getType());
        dto.setGravite(domain.getGravite());
        dto.setReaction(domain.getReaction());

        return dto;
    }

    /**
     * Convertit un DTO en modèle de domaine
     */
    public static Allergie toDomain(AllergieDTO dto) {
        if (dto == null) {
            return null;
        }

        Allergie domain = new Allergie();
        domain.setId(dto.getId());
        domain.setSubstance(dto.getSubstance());
        domain.setType(dto.getType());
        domain.setGravite(dto.getGravite());
        domain.setReaction(dto.getReaction());

        return domain;
    }

    /**
     * Convertit une Entity en DTO (raccourci)
     */
    public static AllergieDTO toDto(AllergieEntity entity) {
        return toDto(toDomain(entity));
    }

    /**
     * Convertit un DTO en Entity (raccourci)
     */
    public static AllergieEntity dtoToEntity(AllergieDTO dto) {
        return toEntity(toDomain(dto));
    }
}
