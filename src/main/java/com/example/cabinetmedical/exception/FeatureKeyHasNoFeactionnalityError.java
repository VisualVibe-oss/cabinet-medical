package com.example.cabinetmedical.exception;


// Exception personnalisée pour indiquer qu'une FeatureKey n'a pas de fonctionnalité associée
// Donc il faut modifier le registry dans BehaviorPackBuilder 
public class FeatureKeyHasNoFeactionnalityError extends RuntimeException {
    
    public FeatureKeyHasNoFeactionnalityError(String message) {
        super(message);
    }
    
}
