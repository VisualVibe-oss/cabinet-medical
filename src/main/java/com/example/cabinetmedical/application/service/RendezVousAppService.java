package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.RendezVousRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class RendezVousAppService {

    private RendezVousRepositoryImpl rendezVousRepository;
    private RendezVousMapper rvm;

    public RendezVousAppService(RendezVousRepositoryImpl rendezVousRepository ) {
        this.rendezVousRepository = rendezVousRepository;
    }

    public RendezVousDTO create(RendezVous rv){

        RendezVousEntity rve = rvm.toEntity(rv);

        return rvm.toDTO(rendezVousRepository.save(rve));
    }
}
