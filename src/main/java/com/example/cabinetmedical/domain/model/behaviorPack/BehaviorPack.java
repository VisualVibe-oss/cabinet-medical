package com.example.cabinetmedical.domain.model.behaviorPack;

import java.util.HashMap;

import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;


import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
public abstract class BehaviorPack {
 
    
    HashMap<Featurekey, Functionnalitie> features = new HashMap<>();
    
    public FeatureResponce performWork(FeatureParameter parameter) {
         return features.get(parameter.getKey()).performWork(parameter);
    }
}
