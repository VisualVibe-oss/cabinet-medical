package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.functionnalities.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.OfferType;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import org.springframework.stereotype.Service;

@Service
public class MedecinAppService {


    private SecretaireMapper sm;
    private MedecinMapper mm;
    private MedecinRepository medecinRepositoryImpl;
    private SecretaireAppService secretaireAppService;

    public MedecinAppService(MedecinMapper mm, SecretaireMapper sm, MedecinRepository medecinRepositoryImpl, SecretaireAppService secretaireAppService) {
        this.mm = mm;
        this.sm = sm;
        this.medecinRepositoryImpl = medecinRepositoryImpl;
        this.secretaireAppService = secretaireAppService;
    }

    public SecretaireDTO addSecretary(SecretaireDTO secretaireDTO, int idMedecin) {

        Medecin medecin = mm.toDomain(medecinRepositoryImpl.find(idMedecin)) ;
        Secretaire secretaire = sm.toDomain(secretaireDTO);

        AddSecretairePayload addSecretairePayload = new AddSecretairePayload();

        secretaire.setCabinet(medecin.getCabinet());

        addSecretairePayload.setMedecin(medecin);
        addSecretairePayload.setSecretaire(secretaire);

        if (medecin.getCabinet().getOffre().getType()== OfferType.BASIC){
            addSecretairePayload.setMaxEmployees(2);
        }
        else if(medecin.getCabinet().getOffre().getType()==OfferType.PRO){
            addSecretairePayload.setMaxEmployees(5);
        }

        FeatureResponce<Secretaire> responce = medecin.getCabinet().getBehaviorPack().performWork(new FeatureParameter<AddSecretairePayload>(Featurekey.ADD_SECRETAIRE, addSecretairePayload));
        Secretaire processedSecretaire = responce.getPayload();

        return secretaireAppService.createSecretaire(processedSecretaire);
    }
}
