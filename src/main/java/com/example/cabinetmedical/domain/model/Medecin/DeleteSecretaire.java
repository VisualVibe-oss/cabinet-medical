package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.utils.payload.DeleteSecretairePayload;
import com.example.cabinetmedical.exception.SecretaireNotInCabinetException;

public class DeleteSecretaire implements Functionnalitie {

    @Override
    public FeatureResponce performWork(FeatureParameter param){
        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }
        if (!(param.getPayload() instanceof Secretaire)){
            throw new IllegalArgumentException("the payload is not of type Secretaire");
        }
        Secretaire secretaire = (Secretaire) param.getPayload();

        if (secretaire == null){
            throw new IllegalArgumentException("secretaire is null");
        }

        return new FeatureResponce<>(Featurekey.DELETE_SECRETAIRE, secretaire);
    }
}
