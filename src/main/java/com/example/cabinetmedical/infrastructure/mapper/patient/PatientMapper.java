package com.example.cabinetmedical.infrastructure.mapper.patient;

import com.example.cabinetmedical.application.dto.patient.PatientDTO;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.mapper.dossierMedical.DossierMedicalMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private final DossierMedicalMapper dossierMedicalMapper;
    public PatientMapper(DossierMedicalMapper dossierMedicalMapper) {
        this.dossierMedicalMapper = dossierMedicalMapper;
    }

    /**
     * Domain -> Entity
     */
    public PatientEntity toEntity(Patient domain) {
        if (domain == null) {
            return null;
        }

        PatientEntity entity = new PatientEntity();
        entity.setIdPatient(domain.getIdPatient());
        entity.setNom(domain.getNom());
        entity.setPrenom(domain.getPrenom());
        entity.setCin(domain.getCin());
        entity.setTelephone(domain.getTelephone());
        entity.setSexe(domain.getSexe());
        entity.setDateNaissance(domain.getDateNaissance());
        entity.setTypeMutuelle(domain.getTypeMutuelle());

        return entity;
    }

    /**
     * Entity -> Domain
     */
    public Patient toDomain(PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        Patient domain = new Patient();
        domain.setIdPatient(entity.getIdPatient());
        domain.setNom(entity.getNom());
        domain.setPrenom(entity.getPrenom());
        domain.setCin(entity. getCin());
        domain.setTelephone(entity.getTelephone());
        domain.setSexe(entity. getSexe());
        domain.setDateNaissance(entity.getDateNaissance());
        domain.setTypeMutuelle(entity. getTypeMutuelle());

        return domain;
    }

    /**
     * Entity -> DTO
     */
    public PatientDTO toDTO(PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        PatientDTO dto = new PatientDTO();
        dto.setIdPatient(entity.getIdPatient());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setCin(entity.getCin());
        dto.setTelephone(entity.getTelephone());
        dto.setSexe(entity.getSexe());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setTypeMutuelle(entity.getTypeMutuelle());
        dto.setIdCabinet(entity.getCabinet().getIdCabinet());
        dto.setDossierMedical(dossierMedicalMapper.toDTO(entity.getDossierMedical()));

        return dto;
    }

    /**
     * Domain -> DTO
     */
    public PatientDTO toDTO(Patient domain) {
        if (domain == null) {
            return null;
        }

        PatientDTO dto = new PatientDTO();
        dto.setIdPatient(domain.getIdPatient());
        dto.setNom(domain.getNom());
        dto.setPrenom(domain.getPrenom());
        dto.setCin(domain.getCin());
        dto.setTelephone(domain.getTelephone());
        dto.setSexe(domain.getSexe());
        dto.setDateNaissance(domain.getDateNaissance());
        dto.setTypeMutuelle(domain.getTypeMutuelle());
        dto.setIdCabinet(domain.getCabinet().getIdCabinet());

        return dto;
    }
}
