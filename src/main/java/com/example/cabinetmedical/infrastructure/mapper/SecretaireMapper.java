package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import org.springframework.stereotype.Component;

import java.util.List;

//TEMPORARY
@Component
public class SecretaireMapper {



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

        se.getPermissionKeys().clear();
        se.getPermissionKeys().addAll(s.getPermissionKeys());

        return se;
    }
    public Secretaire toDomain(SecretaireEntity se){

        Secretaire s = new Secretaire(se.getIdSecretaire(), se.getNom(), se.getPrenom(), se.getEmail(), se.getPassword(), se.getSalaire(), se.getTelephone(),CabinetMapper .toDomain(se.getCabinet()), se.getPermissionKeys());
        return s;
    }
    public Secretaire toDomain(SecretaireDTO sdto) {
        Secretaire s = new Secretaire();

        s.setIdSecretaire(sdto.getIdSecretaire());
        s.setNom(sdto.getNom());
        s.setPrenom(sdto.getPrenom());
        s.setEmail(sdto.getEmail());
        s.setPassword(sdto.getPassword());
        s.setSalaire(sdto.getSalaire());
        s.setTelephone(sdto.getTelephone());
        s.setPermissionKeys(sdto.getPermissionKeys());


        return s;
    }

    public SecretaireDTO toDTO(SecretaireEntity se) {
        SecretaireDTO sdto = new SecretaireDTO();

        sdto.setIdSecretaire(se.getIdSecretaire());
        sdto.setNom(se.getNom());
        sdto.setPrenom(se.getPrenom());
        sdto.setEmail(se.getEmail());
        sdto.setPassword(se.getPassword());
        sdto.setSalaire(se.getSalaire());
        sdto.setTelephone(se.getTelephone());
        sdto.setIdCabinet(se.getCabinet().getIdCabinet());
        sdto.setPermissionKeys(se.getPermissionKeys());

        return sdto;
    }


    public SecretaireDTO toDTO(Secretaire s) {
        SecretaireDTO sdto = new SecretaireDTO();

        sdto.setIdSecretaire(s.getIdSecretaire());
        sdto.setNom(s.getNom());
        sdto.setPrenom(s.getPrenom());
        sdto.setEmail(s.getEmail());
        sdto.setPassword(s.getPassword());
        sdto.setSalaire(s.getSalaire());
        sdto.setTelephone(s.getTelephone());
        sdto.setIdCabinet(s.getCabinet().getIdCabinet());
        sdto.setPermissionKeys(s.getPermissionKeys());

        return sdto;
    }


    public List<Secretaire> toDomainList(List<SecretaireEntity> se){
        return se.stream().map(this::toDomain).toList();
    }

    public List<Secretaire> toDomainListFromDto(List<SecretaireDTO> sdto){return sdto.stream().map(this::toDomain).toList();}

    public List<SecretaireDTO> toDTOList(List<Secretaire> s){
        return s.stream().map(this::toDTO).toList();
    }
}
