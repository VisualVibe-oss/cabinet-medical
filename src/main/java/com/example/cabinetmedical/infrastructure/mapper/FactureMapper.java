package com.example.cabinetmedical.infrastructure.mapper;


import com.example.cabinetmedical.application.dto.FactureDTO;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;

import java.text.SimpleDateFormat;


public class FactureMapper {

    // Format de date standard pour le JSON (ISO 8601)
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FactureDTO toDto(FactureEntity entity) {
        if (entity == null) {
            return null;
        }

        FactureDTO dto = new FactureDTO();
        
        dto.setIdFacture(entity.getIdFacture());
        dto.setType(entity.getType());
        
        // Conversion de int (Entity) vers Float (DTO)
        dto.setPrix((float) entity.getPrix());

        // Conversion sécurisée de la Date vers String
        if (entity.getDate() != null) {
            dto.setDate(dateFormat.format(entity.getDate()));
        }

        // Conversion de l'Enum State vers String
        if (entity.getState() != null) {
            dto.setState(entity.getState().name());
        }

        // Extraction de l'ID du Cabinet (évite d'envoyer tout l'objet Cabinet)
        if (entity.getCabinet() != null) {
            dto.setCabinetId(entity.getCabinet().getIdCabinet());
        }

        return dto;
    }


     private CabinetMapper cm;

    public FactureMapper(CabinetMapper cm) {
        this.cm = cm;
    }

    public FactureEntity toEntity(Facture f) {
        return new FactureEntity(f.getIdFacture(), f.getType(), f.getPrix(), f.getDate(), cm.toEntity(f.getCabinet()));
    }

    public Facture toDomain(FactureEntity fe) {
        return new Facture(fe.getIdFacture(), fe.getType(), fe.getPrix(), fe.getDate(), cm.toDomain(fe.getCabinet()));
    }

    public List<FactureEntity> toEntityList(List<Facture> f) {
        return f.stream().map(this::toEntity).toList();
    }

    public List<Facture> toDomainList(List<FactureEntity> fe) {
        return fe.stream().map(this::toDomain).toList();
    }
}
