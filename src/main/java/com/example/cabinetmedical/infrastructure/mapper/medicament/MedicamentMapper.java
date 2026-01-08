package com.example.cabinetmedical.infrastructure.mapper.medicament;

import com.example.cabinetmedical.application.DTO.Medicament.MedicamentDTO;
import com.example.cabinetmedical.domain.model.medicament.Medicament;
import com.example.cabinetmedical.infrastructure.entity.MedicamentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicamentMapper {
    //dto to domain
    public Medicament toDomain(MedicamentDTO medicamentDTO) {
        if(medicamentDTO== null){
            return null;
        }
        return new Medicament(medicamentDTO.getId(),medicamentDTO.getNom());
    }

    //domain to dto
    public MedicamentDTO toDto(Medicament medicament) {
        if(medicament == null){return null;}
        return new MedicamentDTO(medicament.getId(), medicament.getNom());
    }

    //domain to entity
    public MedicamentEntity toEntity(Medicament medicament) {
        if(medicament == null){
            return null;
        }
        MedicamentEntity entity = new MedicamentEntity();
        entity.setIdMedicament(medicament.getId());
        entity.setNom(medicament.getNom());

        return entity;
    }
    //entity to domain
    public Medicament toDomain(MedicamentEntity medicament) {
        if(medicament==null ){return null;}
        return new Medicament(medicament.getIdMedicament(), medicament.getNom());
    }

    //entity to dto
    public MedicamentDTO toDto(MedicamentEntity medicament){
        if(medicament == null){return null;}
        return new MedicamentDTO(medicament.getIdMedicament(), medicament.getNom());
    }

    //dto to entity
    public MedicamentEntity toEntity(MedicamentDTO medicament) {
        if(medicament==null){return null;}
        MedicamentEntity entity = new MedicamentEntity();
        entity.setIdMedicament(medicament.getId());
        entity.setNom(medicament.getNom());

        return entity;
    }

    //list entities to list dto
    public List<MedicamentDTO> toDtoList(List<MedicamentEntity> medicaments){
        if(medicaments==null){return null;}
        return medicaments.stream().map(this::toDto).collect(Collectors.toList());
    }

    //list domain to list dto
    public List<MedicamentDTO> toDtoListDomain(List<Medicament> medicaments){
        if(medicaments==null){return null;}
        return medicaments.stream().map(this::toDto).collect(Collectors.toList());
    }
}
