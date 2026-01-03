package com.example.cabinetmedical.domain.model.Cabinet;

import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class Cabinet {
      BehaviorPack behaviorPack ;

    private int idCabinet;
    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }



  
    FeatureResponce performWork(FeatureParameter parameter) {
         return behaviorPack.performWork(parameter) ;
    }

}
