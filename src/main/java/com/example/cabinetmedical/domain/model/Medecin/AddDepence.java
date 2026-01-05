package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;

public class AddDepence implements Functionnalitie {

    @Override
    public FeatureResponce performWork(FeatureParameter param) {

        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }
        if (!(param.getPayload() instanceof Depence)){
            throw new IllegalArgumentException("the payload is not of type Depence");
        }
        Depence depence = (Depence) param.getPayload();

        if(depence.getPrix() == null || depence.getPrix() <= 0) {
            throw new IllegalArgumentException("cant have negtive price");
        }

        if(depence.getDescription() == null || depence.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("must have description");
        }

        if(depence.getType() == null) {
            throw new IllegalArgumentException("must have type");
        }

        if(depence.getType() == DepenceType.INITIAL_PERIODIC) {
            if(depence.getAnchorDate() == null) {
                throw new IllegalArgumentException("anchor cant be null for INITIAL_PERIODIC");
            }
            if(depence.getPeriodeJour() <= 0) {
                throw new IllegalArgumentException("must have period>0 for INITIAL_PERIODIC");
            }
        }


        if(depence.getDate() == null) {
            throw new IllegalArgumentException("must have date");
        }

        if(depence.getCabinet() == null) {
            throw new IllegalArgumentException("must have office");
        }

        return new FeatureResponce(Featurekey.ADD_DEPENCE, depence);
    }

}
