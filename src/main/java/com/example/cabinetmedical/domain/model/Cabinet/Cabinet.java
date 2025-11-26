package com.example.cabinetmedical.domain.model.Cabinet;

import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class Cabinet {
      BehaviorPack behaviorPack ; 



  
    FeatureResponce performWork(FeatureParameter parameter) {
         return behaviorPack.performWork(parameter) ;
    }

}
