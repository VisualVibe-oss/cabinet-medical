package com.example.cabinetmedical.infrastructure.mapper.rendezVous;

import com.example.cabinetmedical.application.DTO.PatientDTO;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.patient.PatientMapper;
import org.springframework.stereotype.Component;

@Component
public class RendezVousMapper {

    private final CabinetMapper cabinetMapper;
    private final PatientMapper patientMapper;

    public RendezVousMapper(CabinetMapper cabinetMapper, PatientMapper patientMapper) {
        this.cabinetMapper = cabinetMapper;
        this. patientMapper = patientMapper;
    }

    /**
     * Entity → Domain
     */
    public RendezVous toDomain(RendezVousEntity entity) {
        if (entity == null) {
            return null;
        }

        RendezVous domain = new RendezVous();
        domain.setIdRendezVous(entity.getIdRendezVous());
        domain.setDateDebutRendezVous(entity. getDateDebutRendezVous());
        domain.setMotif(entity.getMotif());
        domain.setStatut(entity.getStatut());
        domain.setNotes(entity.getNotes());

        // Mapper les relations
        

        return domain;
    }

    /**
     * DTO → Domain
     */
    public RendezVous toDomain(RendezVousDTO dto) {
        if (dto == null) {
            return null;
        }

        RendezVous domain = new RendezVous();
        domain.setIdRendezVous(dto.getIdRendezVous() != null ? dto.getIdRendezVous() : 0);
        domain.setDateDebutRendezVous(dto.getDateDebutRendezVous());
        domain.setMotif(dto.getMotif());
        domain.setStatut(dto. getStatut());
        domain.setNotes(dto.getNotes());

        // Créer des objets Cabinet et Patient avec uniquement les IDs
        if (dto.getIdCabinet() != null) {
            Cabinet cabinet = new Cabinet();
            cabinet.setIdCabinet(dto.getIdCabinet());
            domain. setCabinet(cabinet);
        }

        if (dto.getPatient() != null) {
            Patient patient = new Patient();
            patient.setIdPatient(dto.getPatient().getIdPatient());
            domain.setPatient(patient);
        }

        return domain;
    }

    /**
     * Domain → Entity
     */
    public RendezVousEntity toEntity(RendezVous domain) {
        if (domain == null) {
            return null;
        }

        RendezVousEntity entity = new RendezVousEntity();
        if (domain.getIdRendezVous() > 0) {
            entity. setIdRendezVous(domain.getIdRendezVous());
        }
        entity.setDateDebutRendezVous(domain. getDateDebutRendezVous());
        entity.setMotif(domain.getMotif());
        entity.setStatut(domain.getStatut());
        entity.setNotes(domain.getNotes());

        // Note: Les relations (cabinet, patient) doivent être chargées depuis la DB
        // dans le service avant la sauvegarde

        return entity;
    }

    /**
     * Entity → DTO
     */
    public RendezVousDTO toDTO(RendezVousEntity entity) {
        if (entity == null) {
            return null;
        }

        RendezVousDTO dto = new RendezVousDTO();
        dto.setIdRendezVous(entity.getIdRendezVous());
        dto.setDateDebutRendezVous(entity. getDateDebutRendezVous());
        dto.setMotif(entity.getMotif());
        dto.setStatut(entity.getStatut());
        dto.setNotes(entity.getNotes());

        // Mapper les IDs des relations
        if (entity.getCabinet() != null) {
            dto.setIdCabinet(entity.getCabinet().getIdCabinet());
        }

        if (entity.getPatient() != null) {
            PatientDTO patientDTO  = PatientMapper.toDTO(entity.getPatient()) ;
            dto.setPatient() ;
        }

        return dto;
    }

    /**
     * Domain → DTO
     */
    public RendezVousDTO toDTO(RendezVous domain) {
        if (domain == null) {
            return null;
        }

        RendezVousDTO dto = new RendezVousDTO();
        dto.setIdRendezVous(domain.getIdRendezVous());
        dto.setDateRendezVous(domain.getDateRendezVous());
        dto.setMotif(domain.getMotif());
        dto.setStatut(domain.getStatut());
        dto.setNotes(domain.getNotes());

        if (domain.getCabinet() != null) {
            dto.setIdCabinet(domain.getCabinet().getIdCabinet());
        }

        if (domain.getPatient() != null) {
            dto.setIdPatient(domain.getPatient().getIdPatient());
        }

        return dto;
    }
}
