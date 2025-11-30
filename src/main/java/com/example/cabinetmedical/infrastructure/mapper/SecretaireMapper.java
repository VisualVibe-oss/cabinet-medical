package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;

//TEMPORARY

public class SecretaireMapper {

    CabinetMapper cm = new CabinetMapper();

    public SecretaireEntity toEntity(Secretaire s){

        SecretaireEntity se = new SecretaireEntity();

        se.setIdSecretaire(s.getIdSecretaire());
        se.setNom(s.getNom());
        se.setPrenom(s.getPrenom());
        se.setEmail(s.getEmail());
        se.setPassword(s.getPassword());
        se.setSalaire(s.getSalaire());
        se.setTelephone(s.getTelephone());
        se.setCabinet(cm.toEntity(s.getCabinet()));

        return se;
    }
    public Secretaire toDomain(SecretaireEntity se){

        Secretaire s = new Secretaire();
        s.setIdSecretaire(se.getIdSecretaire());
        s.setNom(se.getNom());
        s.setPrenom(se.getPrenom());
        s.setEmail(se.getEmail());
        s.setPassword(se.getPassword());
        s.setSalaire(se.getSalaire());
        s.setTelephone(se.getTelephone());
        s.setCabinet(cm.toDomain(se.getCabinet()));

        return s;
    }
}
