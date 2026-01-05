package com.example.cabinetmedical.infrastructure.mapper;

import java.util.stream.Collectors;

import com.example.cabinetmedical.application.dto.AllergieDTO;
import com.example.cabinetmedical.application.dto.AntecedentChirurgicalDTO;
import com.example.cabinetmedical.application.dto.AntecedentMedicalDTO;
import com.example.cabinetmedical.application.dto.DossierMedicalDetailDTO;
import com.example.cabinetmedical.application.dto.HabitudeVieDTO;
import com.example.cabinetmedical.application.dto.TraitementChroniqueDTO;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.HabitudeVieEntity;

public class DossierMedicalMapper {

    public static DossierMedicalDetailDTO toDetailDTO(DossierMedicalEntity entity) {

        DossierMedicalDetailDTO dto = new DossierMedicalDetailDTO();

        // =======================
        // Antecedents médicaux
        // =======================
        dto.setAntecedentsMedicaux(
            entity.getAntecedentsMedicaux()
                .stream()
                .map(am -> {
                    AntecedentMedicalDTO d = new AntecedentMedicalDTO();
                    d.setId(am.getId());
                    d.setType(am.getType());
                    d.setDescription(am.getDescription());
                    d.setDateDebut(am.getDateDebut());
                    return d;
                })
                .collect(Collectors.toList())
        );

        // =======================
        // Antecedents chirurgicaux
        // =======================
        dto.setAntecedentsChirurgicaux(
            entity.getAntecedentsChirurgicaux()
                .stream()
                .map(ac -> {
                    AntecedentChirurgicalDTO d = new AntecedentChirurgicalDTO();
                    d.setId(ac.getId());
                    d.setIntervention(ac.getIntervention());
                    d.setAnnee(ac.getAnnee());
                    d.setComplications(ac.getComplications());
                    return d;
                })
                .collect(Collectors.toList())
        );

        // =======================
        // Allergies
        // =======================
        dto.setAllergies(
            entity.getAllergies()
                .stream()
                .map(a -> {
                    AllergieDTO d = new AllergieDTO();
                    d.setId(a.getId());
                    d.setSubstance(a.getSubstance());
                    d.setType(a.getType());
                    d.setGravite(a.getGravite());
                    d.setReaction(a.getReaction());
                    return d;
                })
                .collect(Collectors.toList())
        );

        // =======================
        // Traitements chroniques
        // =======================
        dto.setTraitementsChroniques(
            entity.getTraitementsChroniques()
                .stream()
                .map(t -> {
                    TraitementChroniqueDTO d = new TraitementChroniqueDTO();
                    d.setId(t.getId());
                    d.setMedicament(t.getMedicament());
                    d.setDosage(t.getDosage());
                    d.setFrequence(t.getFrequence());
                    d.setDateDebut(t.getDateDebut());
                    return d;
                })
                .collect(Collectors.toList())
        );

        // =======================
        // Habitudes de vie
        // =======================
        if (entity.getHabitudesVie() != null) {
            HabitudeVieEntity hv = entity.getHabitudesVie();
            HabitudeVieDTO hvDto = new HabitudeVieDTO();
            hvDto.setTabac(hv.getTabac());
            hvDto.setAlcool(hv.getAlcool());
            hvDto.setAlimentation(hv.getAlimentation());
            hvDto.setActivitePhysique(hv.getActivitePhysique());
            hvDto.setSommeil(hv.getSommeil());
            dto.setHabitudesVie(hvDto);
        }

        return dto;
    }
}
