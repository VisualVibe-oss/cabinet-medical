package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.dto.SecretaireDTO;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.stereotype.Component;

import java.util.List;

//TEMPORARY
@Component
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

        Secretaire s = new Secretaire(se.getIdSecretaire(), se.getNom(), se.getPrenom(), se.getEmail(), se.getPassword(), se.getSalaire(), se.getTelephone(),cm.toDomain(se.getCabinet()), se.getPermissionKeys());
        return s;
    }
    public Secretaire toDomain(SecretaireDTO sdto){
        Secretaire s = new Secretaire(sdto.getIdSecretaire(), sdto.getNom(), sdto.getPrenom(), sdto.getEmail(), sdto.getPassword(), sdto.getSalaire(), sdto.getTelephone(),sdto.getCabinet(), sdto.getPermissionKeys());
        return s;
    }
    public SecretaireDTO toDTO(SecretaireEntity se){
        SecretaireDTO sdto = new SecretaireDTO( se.getIdSecretaire() ,se.getNom(), se.getPrenom(), se.getEmail(), se.getPassword(), se.getSalaire(), se.getTelephone(),cm.toDomain(se.getCabinet()), se.getPermissionKeys());
        return sdto;
    }

    public SecretaireDTO toDTO(Secretaire s){
        SecretaireDTO sdto = new SecretaireDTO(s.getIdSecretaire(), s.getNom(), s.getPrenom(), s.getEmail(), s.getPassword(), s.getSalaire(), s.getTelephone(),s.getCabinet(), s.getPermissionKeys());
        return sdto;
    }

    public List<Secretaire> toDomainList(List<SecretaireEntity> se){
        return se.stream().map(this::toDomain).toList();
    }

    public List<SecretaireDTO> toDTOList(List<Secretaire> s){
        return s.stream().map(this::toDTO).toList();
    }
}
