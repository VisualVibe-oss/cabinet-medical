package com.example.cabinetmedical.domain.model.behaviorPack;

import java.util.*;

import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.exception.FeatureNotAllowedToPackError;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
public  class BehaviorPack {
 
    
    public BehaviorPack() {
    }

    private HashMap<Featurekey, Functionnalitie> features = new HashMap<>();

    private void verifyKeys(Featurekey key) {
        if (!features.containsKey(key)) {
            throw new FeatureNotAllowedToPackError("Featurekey not found in BehaviorPack: " + key);
        }
    }
    public List<Featurekey> getFeatureKeys() {
        return new ArrayList<>(features.keySet());
    }
    public FeatureResponce performWork(FeatureParameter parameter) {
         verifyKeys(parameter.getKey());
         return features.get(parameter.getKey()).performWork(parameter);
    }

    public void addFeature(Featurekey key, Functionnalitie functionnalitie) {
        features.put(key, functionnalitie);
    }

}
