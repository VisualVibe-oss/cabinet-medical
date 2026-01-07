package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;

import com.example.cabinetmedical.infrastructure.repository.Medecin.MedecinRepositoryImpl;
import com.example.cabinetmedical.infrastructure.repository.Medecin.SpringMedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabinetAppService {
    CabinetRepository cabinetRepository;
    SpringMedecinRepository medecinRepository;
    CabinetMapper cm;
    SpringSecretaireRepository secretaireRepository ;

    public CabinetAppService(CabinetRepository cabinetRepository
        , SpringMedecinRepository medecinRepository, 
        SpringSecretaireRepository secretaireRepository,
        CabinetMapper cm) {
        this.cabinetRepository = cabinetRepository;
        this.secretaireRepository = secretaireRepository ;
        this.medecinRepository = medecinRepository;
        this.cm = cm;
    }



    public Cabinet getCurrentCabinet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<MedecinEntity> medecinEntity = medecinRepository.findByEmail(authentication.getName());
        if(medecinEntity.isPresent()){
            return cm.toDomain(medecinEntity.get().getCabinet());
        }
        throw new RuntimeException("cant find office for connected user");
    }


    //* Retourne le cabinet si le userDto est definie par l id  */
    public Cabinet getCabinetFromUser(UserDTO user) {

        CabinetEntity cabinetEntity;

        if (AuthService.secretaireRole.equals(user.getRole())) {
            cabinetEntity = secretaireRepository.findCabinetBySecretaireId(user.getId());
        } else if (AuthService.medecinRole.equals(user.getRole())) {
            cabinetEntity = medecinRepository.findCabinetByIdMedecin(user.getId());
        } else {
            throw new IllegalArgumentException("Rôle utilisateur invalide : " + user.getRole());
        }

        if (cabinetEntity == null) {
            throw new IllegalArgumentException("Utilisateur invalide : ");
        }

        Cabinet cabinet = CabinetMapper.toDomain(cabinetEntity);
        Medecin medecin = MedecinMapper.toDomain(cabinetEntity.getMedecin()) ;
        cabinet.setMedecin(medecin);

        

        return cabinet;
    }


    //* Retourne le cabinet si le userDto est definie par l email  */

    public Cabinet getCabinetByEmail(UserDTO user) {

        CabinetEntity cabinetEntity;

        if (AuthService.secretaireRole.equals(user.getRole())) {
            cabinetEntity = secretaireRepository.findCabinetBySecretaireEmail(user.getEmail());
        } else if (AuthService.medecinRole.equals(user.getRole())) {
            cabinetEntity = medecinRepository.findCabinetByEmail(user.getEmail());
        } else {
            throw new IllegalArgumentException("Rôle utilisateur invalide : " + user.getRole());
        }

        if (cabinetEntity == null) {
            throw new IllegalArgumentException("Utilisateur invalide : ");
        }

        Cabinet cabinet = CabinetMapper.toDomain(cabinetEntity);
        Medecin medecin = MedecinMapper.toDomain(cabinetEntity.getMedecin()) ;
        cabinet.setMedecin(medecin);

        

        return cabinet;
    }

}
