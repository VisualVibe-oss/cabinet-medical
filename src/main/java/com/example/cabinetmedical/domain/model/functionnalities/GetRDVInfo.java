package com.example.cabinetmedical.domain.model.functionnalities;

import java.util.List;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class GetRDVInfo  implements Functionnalitie <RendezVous, RendezVous >{

    @Override
    public FeatureResponce<RendezVous> performWork(FeatureParameter<RendezVous> parameter) {
            return new FeatureResponce<RendezVous>(parameter.getKey(),parameter.getPayload()) ;

    }
    
}
