package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.dto.MedecinDTO;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedecinMapper {
    CabinetMapper cm = new CabinetMapper();

    public Medecin toDomain(MedecinDTO mdto) {
        Medecin medecin = new Medecin(mdto.getIdMedecin(), mdto.getNom(), mdto.getPrenom(), mdto.getEmail(), mdto.getPassword(), mdto.getSignature(), mdto.getTelephone(), mdto.getCabinet());
        return medecin;
    }

    public Medecin toDomain(MedecinEntity me) {
        Medecin medecin = new Medecin(me.getIdMedecin(), me.getNom(), me.getPrenom(), me.getEmail(), me.getPassword(), me.getSignature(), me.getTelephone(), cm.toDomain(me.getCabinet()));
        return medecin;
    }
    public List<Medecin> toDomainList(List<MedecinEntity> me){
        return me.stream().map(this::toDomain).toList();
    }
}
