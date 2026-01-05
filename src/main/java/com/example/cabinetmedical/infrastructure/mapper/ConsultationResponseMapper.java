package com.example.cabinetmedical.infrastructure.mapper;


import com.example.cabinetmedical.application.dto.ConsultationResponseDTO;
import com.example.cabinetmedical.application.dto.MedicamentResponseDTO;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.MedciamentOrdonnanceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultationResponseMapper {

    public static ConsultationResponseDTO toDTO(ConsultationEntity entity) {
        if (entity == null) return null;

        ConsultationResponseDTO dto = new ConsultationResponseDTO();

        // --- Champs cliniques ---
        dto.setIdConsultation(entity.getIdConsultation());
        dto.setType(entity.getType());
        dto.setMotif(entity.getMotif());
        dto.setSymptome(entity.getSymptome());
        dto.setTemperature(entity.getTemperature());
        dto.setDateSymptome(entity.getDateSymptome());
        dto.setPoid(entity.getPoid());
        dto.setTension(entity.getTension());
        dto.setFrequenceCardiaque(entity.getFrequenceCardiaque());
        dto.setObservationClinique(entity.getObservationClinique());
        dto.setDate(entity.getDate());

        // --- Ordonnance supplémentaire (repos) ---
        if (entity.getOrdonnanceSup() != null) {
            dto.setRepos(entity.getOrdonnanceSup().getReposStrict());
        }

        // --- Ordonnance médicamenteuse ---
        if (entity.getOrdonnanceMed() != null &&
            entity.getOrdonnanceMed().getMedicaments() != null) {

            List<MedicamentResponseDTO> medicaments =
                    entity.getOrdonnanceMed().getMedicaments()
                            .stream()
                            .map(ConsultationResponseMapper::mapMedicament)
                            .collect(Collectors.toList());

            dto.setMedicaments(medicaments);
        }

        return dto;
    }

    private static MedicamentResponseDTO mapMedicament(
            MedciamentOrdonnanceEntity entity
    ) {
        MedicamentResponseDTO dto = new MedicamentResponseDTO();
        dto.setNom(entity.getMedicament().getNom());
        dto.setDosage(entity.getDosage());
        dto.setDuration(entity.getDuration());
        return dto;
    }
}
