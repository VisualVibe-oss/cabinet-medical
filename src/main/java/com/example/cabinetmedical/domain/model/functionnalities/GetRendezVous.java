package com.example.cabinetmedical.domain.model.functionnalities;


import java.util.List;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class GetRendezVous implements Functionnalitie<List<RendezVous>, List<RendezVous>>  {

    @Override
    public FeatureResponce<List<RendezVous>> performWork(FeatureParameter<List<RendezVous>> parameter) {
        return new FeatureResponce<List<RendezVous>>(parameter.getKey(),parameter.getPayload()) ;
    }
    
}
