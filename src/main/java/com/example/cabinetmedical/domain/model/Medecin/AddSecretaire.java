package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

import java.util.List;

public class AddSecretaire implements Functionnalitie {

    @Override
    public FeatureResponce<Secretaire> performWork(FeatureParameter param){
        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }


        if (!(param.getPayload() instanceof AddSecretairePayload)){
            throw new IllegalArgumentException("the payload is not of type AddSecretairePayload");
        }
        AddSecretairePayload payload = (AddSecretairePayload) param.getPayload();

        if (payload.getSecretaire()==null){
            throw new IllegalArgumentException("secretary is null");
        }
        Cabinet cabinet = payload.getSecretaire().getCabinet();
        if (cabinet==null){
            throw new IllegalArgumentException("the office is null");
        }
        List<Secretaire> secretaires = payload.getTotal();
        int currentTotal = secretaires.size();
        if (currentTotal >= payload.getMaxEmployees()){
            return new FeatureResponce(param.getKey(), "Limit Reached for the current pack");
        }

        String email=payload.getSecretaire().getEmail();

        if (payload.getExistingSecretaireEmails()!=null){
            boolean isEmilExists = payload.getExistingSecretaireEmails().stream().anyMatch(existingEmail->existingEmail!=null && existingEmail.equals(email));
            if (isEmilExists){
                return new FeatureResponce(param.getKey(), "the email already exists");
            }
        }
        if (payload.getExistingMedecinEmails()!=null){
            boolean isEmilExists = payload.getExistingMedecinEmails().stream().anyMatch(existingEmail->existingEmail!=null && existingEmail.equals(email));
            if (isEmilExists){
                return new FeatureResponce(param.getKey(), "the email already exists");
            }
        }


        return new FeatureResponce(param.getKey(), payload.getSecretaire());
    }
}
