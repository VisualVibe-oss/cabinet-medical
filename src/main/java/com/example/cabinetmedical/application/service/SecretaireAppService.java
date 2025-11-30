package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.RendezVousDTO.RendezVousDTO;
import com.example.cabinetmedical.domain.model.rendezvous.RendezVous;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class SecretaireAppService {
    private final RendezVousAppService rendezVousAppService;
    private SecretaireRepositoryImpl secretaireRepository;
    private RendezVousMapper rvm;

    public SecretaireAppService(RendezVousAppService service){
        this.rendezVousAppService = service;
    }

    public RendezVousDTO creeRendezVous(int idSecretaire, RendezVousDTO rvdto ){

        RendezVous rv = rvm.toDomain(rvdto);
        Secretaire secretaire = secretaireRepository.findById(idSecretaire);

        FeatureResponce<RendezVous> responce = secretaire.getCabinet().getBehaviorPack().performWork(new FeatureParameter<RendezVous>(Featurekey.CREE_RENDEZ_VOUS, rv));
        RendezVous processedRv = responce.getPayload();

        return rendezVousAppService.create(processedRv);
    }

}
