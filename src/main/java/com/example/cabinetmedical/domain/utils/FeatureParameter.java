package com.example.cabinetmedical.domain.utils;

public class FeatureParameter<T> {
    Featurekey key ; 
    T payload ; 

    FeatureParameter(Featurekey key , T payload){
        this.key = key;
        this.payload = payload;
    }


     public Featurekey getKey() {
        return key;
    }

    public T getPayload() {
        return payload;
    }

    public void setKey(Featurekey key) {
        this.key = key;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
