package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;

public class EditDepence implements Functionnalitie {

    @Override
    public FeatureResponce performWork(FeatureParameter param){
        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }
        if (!(param.getPayload() instanceof Depence)){
            throw new IllegalArgumentException("the payload is not of type Depence");
        }
        Depence depence = (Depence) param.getPayload();

        if (depence == null){
            throw new IllegalArgumentException("depence is null");
        }

        return new FeatureResponce(Featurekey.EDIT_DEPENCE,  depence);
    }
}
