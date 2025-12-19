package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.*;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretaireAppService {
    private final RendezVousAppService rendezVousAppService;
    private final SecretaireRepositoryImpl secretaireRepositoryImpl;
    private RendezVousMapper rvm;
    private SecretaireMapper sm;

    public SecretaireAppService(RendezVousAppService service, SecretaireRepositoryImpl secretaireRepositoryImpl, RendezVousMapper rvm, SecretaireMapper sm) {
        this.rendezVousAppService = service;
        this.secretaireRepositoryImpl = secretaireRepositoryImpl;
        this.rvm = rvm;
        this.sm = sm;
    }

    public RendezVousDTO creeRendezVous(int idSecretaire, RendezVousDTO rvdto ){

        RendezVous rv = rvm.toDomain(rvdto);
        Secretaire secretaire = findSecretaireById(idSecretaire);

        PermissionResponce<RendezVous> responce = (PermissionResponce<RendezVous>) secretaire.doWork(new PermissionParameter<RendezVous>(PermissionKey.CREE_RENDEZ_VOUS, rv));
        RendezVous processedRv = responce.getPayload();

        return rendezVousAppService.create(processedRv);
    }

    public SecretaireDTO saveSecretaire(Secretaire secretaire){
        SecretaireEntity se = sm.toEntity(secretaire);
        return sm.toDTO(secretaireRepositoryImpl.save(se));
    }

    public List<SecretaireDTO> getAllSecretaries(int idCabinet){
        List<SecretaireEntity> entities = secretaireRepositoryImpl.findByidCabinet(idCabinet);
        List<Secretaire> secretaries = sm.toDomainList(entities);

        return sm.toDTOList(secretaries);
    }

    public Secretaire findSecretaireById(int idSecretaire){
        SecretaireEntity se = secretaireRepositoryImpl.findById(idSecretaire);
        System.out.println("found secretary"+se.getIdSecretaire());
        return sm.toDomain(se);
    }
    @Transactional
    public void deleteSecretaire(int idSecretaire){
         secretaireRepositoryImpl.deleteByidSecretaire(idSecretaire);
    }
    public List<Secretaire> findAll(){
        List<SecretaireEntity> ses= secretaireRepositoryImpl.findAll();
        return sm.toDomainList(ses);
    }

}
