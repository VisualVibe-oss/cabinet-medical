package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.Medecin.MedecinRepositoryImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabinetAppService {
    CabinetRepository cabinetRepository;
    MedecinRepositoryImpl medecinRepository;
    CabinetMapper cm;

    public CabinetAppService(CabinetRepository cabinetRepository, MedecinRepositoryImpl medecinRepository, CabinetMapper cm) {
        this.cabinetRepository = cabinetRepository;
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

}
