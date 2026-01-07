package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.AntecedentChirurgical.AntecedentChirurgical;
import com.example.cabinetmedical.infrastructure.entity.AntecedentChirurgicalEntity;
import com.example.cabinetmedical.application.DTO.AntecedentChirurgicalDTO;
import org.springframework.stereotype.Component;

@Component
public class AntecedentChirurgicalMapper {
    public static AntecedentChirurgical toDomain(AntecedentChirurgicalEntity entity) {
        if (entity == null) {
            return null;
        }

        AntecedentChirurgical domain = new AntecedentChirurgical();
        domain.setId(entity.getId());
        domain.setDossierId(entity.getDossierMedical() != null ?
                entity.getDossierMedical().getIdDossier() : 0);
        domain.setIntervention(entity.getIntervention());
        domain.setAnnee(entity.getAnnee());
        domain.setComplications(entity.getComplications());

        return domain;
    }

    public static AntecedentChirurgicalEntity toEntity(AntecedentChirurgical domain) {
        if (domain == null) {
            return null;
        }

        AntecedentChirurgicalEntity entity = new AntecedentChirurgicalEntity();
        entity.setId(domain.getId());
        entity.setIntervention(domain.getIntervention());
        entity.setAnnee(domain.getAnnee());
        entity.setComplications(domain.getComplications());

        return entity;
    }

    public static AntecedentChirurgicalDTO toDto(AntecedentChirurgical domain) {
        if (domain == null) {
            return null;
        }

        AntecedentChirurgicalDTO dto = new AntecedentChirurgicalDTO();
        dto.setId(domain.getId());
        dto.setIntervention(domain.getIntervention());
        dto.setAnnee(domain.getAnnee());
        dto.setComplications(domain.getComplications());

        return dto;
    }

    public static AntecedentChirurgical toDomain(AntecedentChirurgicalDTO dto) {
        if (dto == null) {
            return null;
        }

        AntecedentChirurgical domain = new AntecedentChirurgical();
        domain.setId(dto.getId());
        domain.setIntervention(dto.getIntervention());
        domain.setAnnee(dto.getAnnee());
        domain.setComplications(dto.getComplications());

        return domain;
    }

    public AntecedentChirurgicalDTO entityToDto(AntecedentChirurgicalEntity entity) {
        return toDto(toDomain(entity));
    }

    public AntecedentChirurgicalEntity toEntity(AntecedentChirurgicalDTO dto) {
        return toEntity(toDomain(dto));
    }
}
