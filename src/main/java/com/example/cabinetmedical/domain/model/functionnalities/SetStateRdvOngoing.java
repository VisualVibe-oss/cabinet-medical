package com.example.cabinetmedical.domain.model.functionnalities;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class SetStateRdvOngoing implements Functionnalitie<RendezVous,RendezVous> {

   
    public FeatureResponce<RendezVous> performWork(FeatureParameter<RendezVous> parameter) {
        return new FeatureResponce<RendezVous>(parameter.getKey(), parameter.getPayload()) ;
    }
    
}
