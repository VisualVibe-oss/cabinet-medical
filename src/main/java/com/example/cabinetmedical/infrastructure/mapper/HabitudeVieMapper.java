package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.HabitudeVie.HabitudeVie;
import com.example.cabinetmedical.infrastructure.entity.HabitudeVieEntity;
import com.example.cabinetmedical.application.DTO.HabitudeVieDTO;
import org.springframework.stereotype.Component;

@Component
public class HabitudeVieMapper {
    public static HabitudeVie toDomain(HabitudeVieEntity entity) {
        if (entity == null) {
            return null;
        }

        HabitudeVie domain = new HabitudeVie();
        domain.setDossierId(entity.getId());
        domain.setTabac(entity.getTabac());
        domain.setAlcool(entity.getAlcool());
        domain.setAlimentation(entity.getAlimentation());
        domain.setActivitePhysique(entity.getActivitePhysique());
        domain.setSommeil(entity.getSommeil());

        return domain;
    }

    /**
     * Convertit un modèle de domaine en Entity
     */
    public static HabitudeVieEntity toEntity(HabitudeVie domain) {
        if (domain == null) {
            return null;
        }

        HabitudeVieEntity entity = new HabitudeVieEntity();
        entity.setId(domain.getDossierId());
        // Note: le DossierMedicalEntity doit être défini séparément
        entity.setTabac(domain.getTabac());
        entity.setAlcool(domain.getAlcool());
        entity.setAlimentation(domain.getAlimentation());
        entity.setActivitePhysique(domain.getActivitePhysique());
        entity.setSommeil(domain.getSommeil());

        return entity;
    }

    /**
     * Convertit un modèle de domaine en DTO
     */
    public static HabitudeVieDTO domainToDto(HabitudeVie domain) {
        if (domain == null) {
            return null;
        }

        HabitudeVieDTO dto = new HabitudeVieDTO();
        dto.setTabac(domain.getTabac());
        dto.setAlcool(domain.getAlcool());
        dto.setAlimentation(domain.getAlimentation());
        dto.setActivitePhysique(domain.getActivitePhysique());
        dto.setSommeil(domain.getSommeil());

        return dto;
    }

    /**
     * Convertit un DTO en modèle de domaine
     */
    public static HabitudeVie dtoToDomain(HabitudeVieDTO dto) {
        if (dto == null) {
            return null;
        }

        HabitudeVie domain = new HabitudeVie();
        // Note: dossierId doit être défini séparément car absent du DTO
        domain.setTabac(dto.getTabac());
        domain.setAlcool(dto.getAlcool());
        domain.setAlimentation(dto.getAlimentation());
        domain.setActivitePhysique(dto.getActivitePhysique());
        domain.setSommeil(dto.getSommeil());

        return domain;
    }

    /**
     * Convertit un DTO en modèle de domaine avec dossierId
     */
    public static HabitudeVie dtoToDomain(HabitudeVieDTO dto, int dossierId) {
        HabitudeVie domain = dtoToDomain(dto);
        if (domain != null) {
            domain.setDossierId(dossierId);
        }
        return domain;
    }

    /**
     * Convertit une Entity en DTO (raccourci)
     */
    public static HabitudeVieDTO entityToDto(HabitudeVieEntity entity) {
        return domainToDto(toDomain(entity));
    }

    /**
     * Convertit un DTO en Entity (raccourci)
     */
    public static HabitudeVieEntity dtoToEntity(HabitudeVieDTO dto) {
        return toEntity(dtoToDomain(dto));
    }
}
