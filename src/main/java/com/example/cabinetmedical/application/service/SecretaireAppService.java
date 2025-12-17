package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class SecretaireAppService {
    private final RendezVousAppService rendezVousAppService;
    private final SecretaireRepositoryImpl secretaireRepositoryImpl;
    private SecretaireRepositoryImpl secretaireRepository;
    private RendezVousMapper rvm;
    private SecretaireMapper sm;

    public SecretaireAppService(RendezVousAppService service, SecretaireRepositoryImpl secretaireRepositoryImpl){
        this.rendezVousAppService = service;
        this.secretaireRepositoryImpl = secretaireRepositoryImpl;
    }

    public RendezVousDTO creeRendezVous(int idSecretaire, RendezVousDTO rvdto ){

        RendezVous rv = rvm.toDomain(rvdto);
        Secretaire secretaire = sm.toDomain(secretaireRepositoryImpl.findById(idSecretaire));

        FeatureResponce<RendezVous> responce = secretaire.getCabinet().getBehaviorPack().performWork(new FeatureParameter<RendezVous>(Featurekey.CREE_RENDEZ_VOUS, rv));
        RendezVous processedRv = responce.getPayload();

        return rendezVousAppService.create(processedRv);
    }

    public SecretaireDTO createSecretaire(Secretaire secretaire){
        SecretaireEntity se = sm.toEntity(secretaire);
        return sm.toDTO(secretaireRepositoryImpl.save(se));
    }

}
