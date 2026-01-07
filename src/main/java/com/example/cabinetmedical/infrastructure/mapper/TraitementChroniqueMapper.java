package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.TraitementChronique.TraitementChronique;
import com.example.cabinetmedical.application.DTO.TraitementChroniqueDTO;
import com.example.cabinetmedical.infrastructure.entity.TraitementChroniqueEntity;

public class TraitementChroniqueMapper {
    public static TraitementChronique toDomain(TraitementChroniqueEntity entity) {
        if (entity == null) {
            return null;
        }

        TraitementChronique domain = new TraitementChronique();
        domain.setId(entity.getId());
        domain.setDossierId(entity.getDossierMedical() != null ?
                entity.getDossierMedical().getIdDossier() : 0);
        domain.setMedicament(entity.getMedicament());
        domain.setDosage(entity.getDosage());
        domain.setFrequence(entity.getFrequence());
        domain.setDateDebut(entity.getDateDebut());

        return domain;
    }

    public static TraitementChroniqueEntity toEntity(TraitementChronique domain) {
        if (domain == null) {
            return null;
        }

        TraitementChroniqueEntity entity = new TraitementChroniqueEntity();
        entity.setId(domain.getId());
        entity.setMedicament(domain.getMedicament());
        entity.setDosage(domain.getDosage());
        entity.setFrequence(domain.getFrequence());
        entity.setDateDebut(domain.getDateDebut());

        return entity;
    }

    public static TraitementChroniqueDTO toDto(TraitementChronique domain) {
        if (domain == null) {
            return null;
        }

        TraitementChroniqueDTO dto = new TraitementChroniqueDTO();
        dto.setId(domain.getId());
        dto.setMedicament(domain.getMedicament());
        dto.setDosage(domain.getDosage());
        dto.setFrequence(domain.getFrequence());
        dto.setDateDebut(domain.getDateDebut());

        return dto;
    }

    public static TraitementChronique toDomain(TraitementChroniqueDTO dto) {
        if (dto == null) {
            return null;
        }

        TraitementChronique domain = new TraitementChronique();
        domain.setId(dto.getId());
        domain.setMedicament(dto.getMedicament());
        domain.setDosage(dto.getDosage());
        domain.setFrequence(dto.getFrequence());
        domain.setDateDebut(dto.getDateDebut());

        return domain;
    }

    public static TraitementChroniqueDTO entityToDto(TraitementChroniqueEntity entity) {
        return toDto(toDomain(entity));
    }

    public static TraitementChroniqueEntity dtoToEntity(TraitementChroniqueDTO dto) {
        return toEntity(toDomain(dto));
    }
}
