package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.behaviorPackBuilder.BehaviorPackBuilder;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.*;
import com.example.cabinetmedical.domain.utils.payload.SecretaryCheck;
import com.example.cabinetmedical.exception.SecretaireNotFoundException;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretaireAppService {
    private final RendezVousAppService rendezVousAppService;
    private final SecretaireRepositoryImpl secretaireRepositoryImpl;
    private final CabinetRepository cabinetRepository;
    private RendezVousMapper rvm;
    private SecretaireMapper sm;
    private CabinetMapper cm;

    public SecretaireAppService(RendezVousAppService service, SecretaireRepositoryImpl secretaireRepositoryImpl, CabinetRepository cabinetRepository, RendezVousMapper rvm, SecretaireMapper sm, CabinetMapper cm) {
        this.rendezVousAppService = service;
        this.secretaireRepositoryImpl = secretaireRepositoryImpl;
        this.cabinetRepository = cabinetRepository;
        this.rvm = rvm;
        this.sm = sm;
        this.cm = cm;
    }

    public RendezVousDTO creeRendezVous(int idSecretaire, RendezVousDTO rvdto ){

        int idCabinet = rvdto.getIdCabinet();
        RendezVous rv = rvm.toDomain(rvdto);
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));
        Secretaire secretaire = findByidSecretaire(idSecretaire);
        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());
        SecretaryCheck payload = new SecretaryCheck(secretaire, new PermissionParameter(PermissionKey.CREE_RENDEZ_VOUS, rv));


        FeatureResponce<RendezVous> responce = behaviorPack.performWork(new FeatureParameter(Featurekey.CREE_RENDEZ_VOUS, payload));
        RendezVous processedRv = responce.getPayload();

        return rendezVousAppService.create(processedRv, idCabinet);
    }

    public Secretaire saveSecretaire(Secretaire secretaire, int idCabinet){
        SecretaireEntity se = sm.toEntity(secretaire);
        se.setCabinet(cabinetRepository.findByIdCabinet(idCabinet));
        return sm.toDomain(secretaireRepositoryImpl.save(se));
    }


    public List<SecretaireDTO> getAllSecretaries(int idCabinet){
        List<SecretaireEntity> entities = secretaireRepositoryImpl.findByidCabinet(idCabinet);
        List<Secretaire> secretaries = sm.toDomainList(entities);

        return sm.toDTOList(secretaries);
    }

    @Transactional
    public void delete(Secretaire secretaire, int idCabinet){

        SecretaireEntity entity = sm.toEntity(secretaire);
        entity.setCabinet(cabinetRepository.findByIdCabinet(idCabinet));

        secretaireRepositoryImpl.delete(entity);
    }


    public List<Secretaire> findAll(){
        List<SecretaireEntity> ses= secretaireRepositoryImpl.findAll();
        return sm.toDomainList(ses);
    }

    public Secretaire findByidSecretaire(int idSecretaire){
        Secretaire s =  sm.toDomain(secretaireRepositoryImpl.findById(idSecretaire).get());
        return s;
    }

}
