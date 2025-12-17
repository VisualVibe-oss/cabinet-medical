package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.dto.SecretaireDTO;
import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.OfferType;
import com.example.cabinetmedical.domain.utils.payload.DeleteSecretairePayload;
import com.example.cabinetmedical.domain.utils.payload.EditSecretairePayload;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MedecinAppService {


    private SecretaireMapper sm;
    private MedecinMapper mm;
    private MedecinRepository medecinRepositoryImpl;
    private SecretaireAppService secretaireAppService;
    private SecretaireRepositoryImpl secretaireRepositoryImpl;

    public MedecinAppService(MedecinMapper mm, SecretaireMapper sm, MedecinRepository medecinRepositoryImpl, SecretaireAppService secretaireAppService,  SecretaireRepositoryImpl secretaireRepositoryImpl) {
        this.mm = mm;
        this.sm = sm;
        this.medecinRepositoryImpl = medecinRepositoryImpl;
        this.secretaireAppService = secretaireAppService;
        this.secretaireRepositoryImpl = secretaireRepositoryImpl;
    }

    public SecretaireDTO addSecretary(SecretaireDTO secretaireDTO, int idMedecin) {

        Medecin medecin = mm.toDomain(medecinRepositoryImpl.find(idMedecin)) ;
        Secretaire secretaire = sm.toDomain(secretaireDTO);

        String upperEmail = secretaire.getEmail().toUpperCase();
        secretaire.setEmail(upperEmail);

        List<String> allSecretaryEmails = secretaireAppService.findAll()
                .stream()
                .map(Secretaire::getEmail)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> allDoctorEmails = this.findAll()
                .stream()
                .map(Medecin::getEmail)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        AddSecretairePayload addSecretairePayload = new AddSecretairePayload();

        secretaire.setCabinet(medecin.getCabinet());
        secretaire.setPermissionKeys(null);

        addSecretairePayload.setMedecin(medecin);
        addSecretairePayload.setSecretaire(secretaire);
        addSecretairePayload.setExistingMedecinEmails(allDoctorEmails);
        addSecretairePayload.setExistingSecretaireEmails(allSecretaryEmails);

        if (medecin.getCabinet().getOffre().getType()== OfferType.BASIC){
            addSecretairePayload.setMaxEmployees(2);
        }
        else if(medecin.getCabinet().getOffre().getType()==OfferType.PRO){
            addSecretairePayload.setMaxEmployees(5);
        }

        FeatureResponce<Secretaire> responce = medecin.getCabinet().getBehaviorPack().performWork(new FeatureParameter<AddSecretairePayload>(Featurekey.ADD_SECRETAIRE, addSecretairePayload));
        Secretaire processedSecretaire = responce.getPayload();

        return secretaireAppService.saveSecretaire(processedSecretaire);
    }

    public List<SecretaireDTO> getAllSecretaries(int idCabinet){
        return secretaireAppService.getAllSecretaries(idCabinet);
    }

    public SecretaireDTO updateSecretaire(int idSecretaire,SecretaireDTO secretaireDTO, int idMedecin) {

        Medecin medecin = mm.toDomain(medecinRepositoryImpl.find(idMedecin)) ;
        Secretaire current = secretaireAppService.findSecretaireById(idSecretaire);

        EditSecretairePayload payload = new EditSecretairePayload(
                current,
                secretaireDTO.getNom(),
                secretaireDTO.getPrenom(),
                secretaireDTO.getEmail(),
                secretaireDTO.getTelephone(),
                secretaireDTO.getSalaire(),
                secretaireDTO.getPermissionKeys()
        );

        FeatureResponce<Secretaire> responce = medecin.getCabinet().getBehaviorPack().performWork(new FeatureParameter(Featurekey.EDIT_SECRETAIRE, payload));
        Secretaire processedSecretaire = responce.getPayload();

        return secretaireAppService.saveSecretaire(processedSecretaire);
    }
    public void deleteSecretaire(int idSecretaire, int idMedecin) {

        Medecin medecin = mm.toDomain(medecinRepositoryImpl.find(idMedecin));
        Secretaire secretaire = secretaireAppService.findSecretaireById(idSecretaire);
        Cabinet cabinet = secretaire.getCabinet();
        DeleteSecretairePayload payload = new DeleteSecretairePayload(secretaire,cabinet);


        FeatureResponce<Boolean> responce = medecin.getCabinet().getBehaviorPack().performWork(new FeatureParameter(Featurekey.DELETE_SECRETAIRE, secretaire));

         secretaireAppService.deleteSecretaire(idSecretaire);
    }
    public List<Medecin> findAll(){
        List<MedecinEntity> mes= medecinRepositoryImpl.findAll();
        return mm.toDomainList(mes);
    }
}
