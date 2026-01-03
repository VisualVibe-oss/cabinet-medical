package com.example.cabinetmedical.infrastructure.mapper.rendezVous;

import com.example.cabinetmedical.application.dto.rendezVous.RendezVousDTO;
import com.example.cabinetmedical.domain.model.rendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.stereotype.Component;

@Component
public class RendezVousMapper {
    public RendezVousDTO toDTO(RendezVousEntity entity) {
        if (entity == null) return null;

        return RendezVousDTO.builder()
                .idRendezVous(entity.getIdRendezVous())
                .dateRendezVous(entity.getDateRendezVous())
                .motif(entity.getMotif())
                .statut(entity.getStatut())
                .notes(entity.getNotes())
                .idCabinet(entity.getCabinet() != null ? entity.getCabinet().getIdCabinet() : null)
                .idPatient(entity. getPatient() != null ? entity.getPatient().getIdPatient() : null)
                .nomPatient(entity.getPatient() != null ? entity.getPatient().getNom() : null)
                .prenomPatient(entity.getPatient() != null ? entity.getPatient().getPrenom() : null)
                .telephonePatient(entity.getPatient() != null ? entity.getPatient().getTelephone() : null)
                .cinPatient(entity.getPatient() != null ? entity.getPatient().getCin() : null)
                .build();
    }

    // Domain to DTO
    public RendezVousDTO toDTO(RendezVous rv) {
        if (rv == null) return null;

        return RendezVousDTO.builder()
                .idRendezVous(rv.getIdRendezVous())
                .dateRendezVous(rv. getDateRendezVous())
                .motif(rv.getMotif())
                .statut(rv.getStatut())
                .notes(rv.getNotes())
                .idCabinet(rv.getCabinet() != null ? rv.getCabinet().getIdCabinet() : null)
                .idPatient(rv.getPatient() != null ? rv.getPatient().getIdPatient() : null)
                .nomPatient(rv. getPatient() != null ? rv.getPatient().getNom() : null)
                .prenomPatient(rv.getPatient() != null ? rv.getPatient().getPrenom() : null)
                .telephonePatient(rv.getPatient() != null ? rv.getPatient().getTelephone() : null)
                .cinPatient(rv. getPatient() != null ? rv.getPatient().getCin() : null)
                .build();
    }

    // DTO to domain
    public RendezVous toDomain(RendezVousDTO dto) {
        if (dto == null) return null;

        RendezVous rv = new RendezVous();

        if (dto.getIdRendezVous() != null) {
            rv.setIdRendezVous(dto.getIdRendezVous());
        }

        rv.setDateRendezVous(dto.getDateRendezVous());
        rv.setMotif(dto.getMotif());
        rv.setStatut(dto.getStatut());
        rv.setNotes(dto.getNotes());

        // Cabinet et Patient seront settés par le service
        return rv;
    }

    // Entity to domain
    public RendezVous toDomain(RendezVousEntity entity) {
        if (entity == null) return null;

        RendezVous rv = new RendezVous();
        rv.setIdRendezVous(entity.getIdRendezVous());
        rv.setDateRendezVous(entity.getDateRendezVous());
        rv.setMotif(entity.getMotif());
        rv.setStatut(entity.getStatut());
        rv.setNotes(entity.getNotes());


        return rv;
    }

    // Domain to ent
    public RendezVousEntity toEntity(RendezVous rv) {
        if (rv == null) return null;

        RendezVousEntity entity = new RendezVousEntity();
        entity.setIdRendezVous(rv.getIdRendezVous());
        entity.setDateRendezVous(rv.getDateRendezVous());
        entity.setMotif(rv.getMotif());
        entity.setStatut(rv.getStatut());
        entity.setNotes(rv.getNotes());

        // Les relations (Cabinet, Patient) seront déjà settées dans le service
        // On les copie directement depuis le domain
        if (rv.getCabinet() != null) {
            // Créer une CabinetEntity minimale juste avec l'ID
            com.example.cabinetmedical.infrastructure.entity.CabinetEntity cabinetEntity =
                    new com.example.cabinetmedical.infrastructure.entity.CabinetEntity();
            cabinetEntity.setIdCabinet(rv.getCabinet().getIdCabinet());
            entity.setCabinet(cabinetEntity);
        }

        if (rv.getPatient() != null) {
            // Créer une PatientEntity minimale juste avec l'ID
            com.example.cabinetmedical.infrastructure.entity.PatientEntity patientEntity =
                    new com. example.cabinetmedical. infrastructure.entity.PatientEntity();
            patientEntity.setIdPatient(rv.getPatient().getIdPatient());
            entity.setPatient(patientEntity);
        }

        return entity;
    }
}
