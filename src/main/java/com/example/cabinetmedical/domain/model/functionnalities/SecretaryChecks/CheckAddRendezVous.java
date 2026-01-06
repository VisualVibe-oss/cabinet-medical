package com.example.cabinetmedical.domain.model.functionnalities.SecretaryChecks;

import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.PermissionResponce;
import com.example.cabinetmedical.domain.utils.payload.SecretaryCheck;

public class CheckAddRendezVous implements Functionnalitie {
    @Override
    public FeatureResponce performWork(FeatureParameter param){
        SecretaryCheck payload = (SecretaryCheck) param.getPayload();
        Secretaire secretaire = payload.getSecretaire();

        PermissionResponce<?> responce = secretaire.doWork(payload.getPermissionParameter());

        return new FeatureResponce(param.getKey(), responce);
    }
}
