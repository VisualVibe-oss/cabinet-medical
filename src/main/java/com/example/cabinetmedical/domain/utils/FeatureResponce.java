package com.example.cabinetmedical.domain.utils;

public class FeatureResponce <T> {
    Featurekey key ; 
    T payload ; 

    FeatureResponce(Featurekey key , T payload){
        this.key = key;
        this.payload = payload;
    }
 
}
