package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactureMapper {
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
