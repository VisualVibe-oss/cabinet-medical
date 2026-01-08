package com.example.cabinetmedical.infrastructure.mapper.dossierMedical;

import com.example.cabinetmedical.application.DTO.AllergieDTO;
import com.example.cabinetmedical.application.DTO.AntecedentChirurgicalDTO;
import com.example.cabinetmedical.application.DTO.AntecedentMedicalDTO;
import com.example.cabinetmedical.application.DTO.DossierMedicalDetailDTO;
import com.example.cabinetmedical.application.DTO.HabitudeVieDTO;
import com.example.cabinetmedical.application.DTO.TraitementChroniqueDTO;
import com.example.cabinetmedical.application.DTO.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.HabitudeVieEntity;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class DossierMedicalMapper {
    //entiy to domain
    public static  DossierMedical toDomain(DossierMedicalEntity entity){
        if(entity == null){return null;}

        DossierMedical dossierMedical = new  DossierMedical() ;

        dossierMedical.setIdDossier(entity.getIdDossier());
        dossierMedical.setDateCreation(entity.getDateCreation());
        return dossierMedical ; 

    }
    //domain to entity
    public  static DossierMedicalEntity toEntity(DossierMedical domain){
        if(domain == null){return null;}

        DossierMedicalEntity dossierMedical  = new DossierMedicalEntity() ; 
        dossierMedical.setIdDossier(domain.getIdDossier());
        dossierMedical.setDateCreation(domain.getDateCreation());
        return dossierMedical ; 
    }

    //dto to domain
    public  static DossierMedical toDomain(DossierMedicalDTO dto){
        if(dto == null){return null;}


        DossierMedical dossierMedical  = new DossierMedical() ; 
        dossierMedical.setIdDossier(dto.getIdDossier());
        dossierMedical.setDateCreation(dto.getDateCreation());
        return dossierMedical ; 
    }


    //domain to dto
    public static DossierMedicalDTO toDto(DossierMedical domain){
        if(domain == null){return null;}

        DossierMedicalDTO dossierMedical  = new DossierMedicalDTO() ; 
        dossierMedical.setIdDossier(domain.getIdDossier());
        dossierMedical.setDateCreation(domain.getDateCreation());
        return dossierMedical ; 
    }

    //entity to dto
    public static DossierMedicalDTO toDTO(DossierMedicalEntity entity) {
        if (entity == null) {
            return null;
        }
        DossierMedicalDTO dossierMedical  = new DossierMedicalDTO() ; 
        dossierMedical.setIdDossier(entity.getIdDossier());
        dossierMedical.setDateCreation(entity.getDateCreation());
        return dossierMedical ; 
    }

    //dto to entity
    public static DossierMedicalEntity toEntity(DossierMedicalDTO dto) {
        if (dto == null) {
            return null;
        }
      DossierMedicalEntity dossierMedical  = new DossierMedicalEntity() ; 
        dossierMedical.setIdDossier(dto.getIdDossier());
        dossierMedical.setDateCreation(dto.getDateCreation());
        return dossierMedical ; 

       
    }






    //* Ce  mapper mapper aussis les entites contenu dans l'argument  */
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
