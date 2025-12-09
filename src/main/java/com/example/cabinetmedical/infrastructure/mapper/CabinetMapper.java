package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import org.springframework.stereotype.Component;

//TEMPORARY
@Component
public class CabinetMapper {

    public CabinetEntity toEntity(Cabinet cabinet) {
        CabinetEntity cabinetEntity = new CabinetEntity();
        return cabinetEntity;
    }
    public Cabinet toDomain(CabinetEntity cabinetEntity) {
        Cabinet cabinet = new Cabinet();
        return cabinet;
    }
}
