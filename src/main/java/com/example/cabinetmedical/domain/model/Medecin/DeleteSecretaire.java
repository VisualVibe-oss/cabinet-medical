package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.DeleteSecretairePayload;
import com.example.cabinetmedical.exception.SecretaireNotInCabinetException;

public class DeleteSecretaire implements Functionnalitie {

    @Override
    public FeatureResponce<Boolean> performWork(FeatureParameter param){
        DeleteSecretairePayload payload = (DeleteSecretairePayload) param.getPayload();
        Cabinet cabinet = payload.getCabinet();
        Secretaire secretaire = payload.getSecretaire();
        if (!cabinet.getSecretaire().contains(secretaire)) {
            throw new SecretaireNotInCabinetException("the secretary does not belong to said office");
        }

        return new FeatureResponce<>(Featurekey.DELETE_SECRETAIRE, true);
    }
}
