package com.example.cabinetmedical.infrastructure.mapper.dossierMedical;

import com.example.cabinetmedical.application.dto.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.domain.model.dossierMedical.DossierMedical;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import org.springframework.stereotype.Component;

@Component
public class DossierMedicalMapper {
    //entiy to domain
    public DossierMedical toDomain(DossierMedicalEntity entity){
        if(entity == null){return null;}
        return new DossierMedical(entity.getIdDossier(),entity.getAntMedicaux(), entity.getAntChirug(),
                entity.getAllergies(), entity.getTraitement(), entity.getHabitudes(),
                entity.getHistoriqueConsultations(), entity.getDocumentsMedicaux(),entity.getDateCreation(),null,null);

    }
    //domain to entity
    public DossierMedicalEntity toEntity(DossierMedical domain){
        if(domain == null){return null;}
        return  new DossierMedicalEntity(domain.getIdDossier(), domain.getAntMedicaux(), domain.getAntChirug()
                , domain.getAllergies(), domain.getTraitement(), domain.getHabitudes(), domain.getHistoriqueConsultations()
                ,domain.getDocumentsMedicaux(), domain.getDateCreation(), null, null);
    }

    //dto to domain
    public DossierMedical toDomain(DossierMedicalDTO dto){
        if(dto == null){return null;}
        return new DossierMedical(dto.getIdDossier(),dto.getAntMedicaux(), dto.getAntChirug(),
                dto.getAllergies(), dto.getTraitement(), dto.getHabitudes(),
                dto.getHistoriqueConsultations(), dto.getDocumentsMedicaux(),dto.getDateCreation(),null,null);
    }


    //domain to dto
    public DossierMedicalDTO toDto(DossierMedical domain){
        if(domain == null){return null;}
        return DossierMedicalDTO.builder().
                idDossier(domain.getIdDossier())
                .antMedicaux(domain.getAntMedicaux())
                .antChirug(domain.getAntChirug())
                .allergies(domain.getAllergies())
                .traitement(domain.getTraitement())
                .habitudes(domain.getHabitudes())
                .historiqueConsultations(domain.getHistoriqueConsultations())
                .documentsMedicaux(domain.getDocumentsMedicaux())
                .dateCreation(domain.getDateCreation())
        .build();
    }

    //entity to dto
    public DossierMedicalDTO toDTO(DossierMedicalEntity entity) {
        if (entity == null) {
            return null;
        }
        return DossierMedicalDTO.builder().
                idDossier(entity.getIdDossier())
                .antMedicaux(entity.getAntMedicaux())
                .antChirug(entity.getAntChirug())
                .allergies(entity.getAllergies())
                .traitement(entity.getTraitement())
                .habitudes(entity.getHabitudes())
                .historiqueConsultations(entity.getHistoriqueConsultations())
                .documentsMedicaux(entity.getDocumentsMedicaux())
                .dateCreation(entity.getDateCreation())
                .build();
    }

    //dto to entity
    public DossierMedicalEntity toEntity(DossierMedicalDTO dto) {
        if (dto == null) {
            return null;
        }
        DossierMedicalEntity entity = new DossierMedicalEntity();

        entity.setAntMedicaux(dto.getAntMedicaux());
        entity.setAntChirug(dto.getAntChirug());
        entity.setAllergies(dto.getAllergies());
        entity.setTraitement(dto.getTraitement());
        entity.setHabitudes(dto.getHabitudes());
        entity.setHistoriqueConsultations(dto.getHistoriqueConsultations());
        entity.setDocumentsMedicaux(dto.getDocumentsMedicaux());
        entity.setDateCreation(dto.getDateCreation());

        return entity;
    }

}
