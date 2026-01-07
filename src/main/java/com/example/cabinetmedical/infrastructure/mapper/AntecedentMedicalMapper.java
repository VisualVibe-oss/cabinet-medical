package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.AntecedantMedical.AntecedentMedical;
import com.example.cabinetmedical.infrastructure.entity.AntecedentMedicalEntity;
import com.example.cabinetmedical.application.DTO.AntecedentMedicalDTO;
import org.springframework.stereotype.Component;

@Component
public class AntecedentMedicalMapper {

    /**
     * Convertit une Entity en modèle de domaine
     */
    public static AntecedentMedical toDomain(AntecedentMedicalEntity entity) {
        if (entity == null) {
            return null;
        }

        AntecedentMedical domain = new AntecedentMedical();
        domain.setId(entity.getId());
        domain.setDossierId(entity.getDossierMedical() != null ?
                entity.getDossierMedical().getIdDossier() : 0);
        domain.setType(entity.getType());
        domain.setDescription(entity.getDescription());
        domain.setDateDebut(entity.getDateDebut());

        return domain;
    }

    /**
     * Convertit un modèle de domaine en Entity
     */
    public static AntecedentMedicalEntity toEntity(AntecedentMedical domain) {
        if (domain == null) {
            return null;
        }

        AntecedentMedicalEntity entity = new AntecedentMedicalEntity();
        entity.setId(domain.getId());
        // Note: le DossierMedicalEntity doit être défini séparément
        entity.setType(domain.getType());
        entity.setDescription(domain.getDescription());
        entity.setDateDebut(domain.getDateDebut());

        return entity;
    }

    /**
     * Convertit un modèle de domaine en DTO
     */
    public static AntecedentMedicalDTO toDto(AntecedentMedical domain) {
        if (domain == null) {
            return null;
        }

        AntecedentMedicalDTO dto = new AntecedentMedicalDTO();
        dto.setId(domain.getId());
        dto.setType(domain.getType());
        dto.setDescription(domain.getDescription());
        dto.setDateDebut(domain.getDateDebut());

        return dto;
    }

    /**
     * Convertit un DTO en modèle de domaine
     */
    public static AntecedentMedical toDomain(AntecedentMedicalDTO dto) {
        if (dto == null) {
            return null;
        }

        AntecedentMedical domain = new AntecedentMedical();
        domain.setId(dto.getId());
        domain.setType(dto.getType());
        domain.setDescription(dto.getDescription());
        domain.setDateDebut(dto.getDateDebut());

        return domain;
    }

    /**
     * Convertit une Entity en DTO (raccourci)
     */
    public AntecedentMedicalDTO entityToDto(AntecedentMedicalEntity entity) {
        return toDto(toDomain(entity));
    }

    /**
     * Convertit un DTO en Entity (raccourci)
     */
    public static AntecedentMedicalEntity dtoToEntity(AntecedentMedicalDTO dto) {
        return toEntity(toDomain(dto));
    }
}
