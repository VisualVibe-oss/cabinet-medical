package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.DTO.DepenceDTO;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.infrastructure.entity.DepenceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepenceMapper {
    CabinetMapper cm;
    public DepenceMapper(CabinetMapper cm) {
        this.cm = cm;
    }

    public DepenceEntity toEntity(Depence d){
        DepenceEntity e = new DepenceEntity();
        e.setIdDepence(d.getIdDepence());
        e.setDescription(d.getDescription());
        e.setPrix(d.getPrix());
        e.setType(d.getType());
        e.setDate(d.getDate());
        e.setPeriodeJour(d.getPeriodeJour());
        e.setLastGenratedDate(d.getLastGeneratedDate());
        e.setAnchorDate(d.getAnchorDate());
        return e;
    }

    public Depence toDomain(DepenceEntity de){

        Depence e = new Depence();
        e.setIdDepence(de.getIdDepence());
        e.setDescription(de.getDescription());
        e.setPrix(de.getPrix());
        e.setType(de.getType());
        e.setDate(de.getDate());
        e.setPeriodeJour(de.getPeriodeJour());
        e.setLastGeneratedDate(de.getLastGenratedDate());
        e.setAnchorDate(de.getAnchorDate());
        return e;
    }

    public Depence toDomain(DepenceDTO dto){
        Depence d = new Depence();
        d.setIdDepence(dto.getIdDepence());
        d.setDescription(dto.getDescription());
        d.setPrix(dto.getPrix());
        d.setDate(dto.getDate());
        d.setPeriodeJour(dto.getPeriodeJour());
        d.setType(dto.getType());
        return d;
    }
    public DepenceDTO toDTO(Depence d){
        DepenceDTO dto = new DepenceDTO();
        dto.setIdDepence(d.getIdDepence());
        dto.setDescription(d.getDescription());
        dto.setPrix(d.getPrix());
        dto.setType(d.getType());
        dto.setDate(d.getDate());
        dto.setPeriodeJour(d.getPeriodeJour());
        dto.setIdCabinet(d.getCabinet().getIdCabinet());
        return dto;
    }

    public List<DepenceEntity> toEntityList(List<Depence> d){
        return d.stream().map(this::toEntity).toList();
    }
    public List<Depence> toDomainList(List<DepenceEntity> de){
        return de.stream().map(this::toDomain).toList();
    }
}
