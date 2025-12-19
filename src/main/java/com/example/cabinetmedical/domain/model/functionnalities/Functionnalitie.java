package com.example.cabinetmedical.domain.model.functionnalities;

import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public interface Functionnalitie<T,R> {

    FeatureResponce<R> performWork(FeatureParameter<T> parameter) ;
    
} 