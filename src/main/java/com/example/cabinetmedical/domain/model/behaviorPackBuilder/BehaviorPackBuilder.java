package com.example.cabinetmedical.domain.model.behaviorPackBuilder;

import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.functionnalities.AddSecretaire;
import com.example.cabinetmedical.domain.model.functionnalities.CreeRendezVous;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.functionnalities.FunctionnalitieTest;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.exception.FeatureKeyHasNoFeactionnalityError;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.Map;
import java.util.function.Supplier;


public class BehaviorPackBuilder {
    
    //* registry contient les associations entre les Featurekey et les fournisseurs de Functionnalitie correspondants */
   private static Map<Featurekey, Supplier<Functionnalitie>> registry = Map.of(
        Featurekey.TEST, FunctionnalitieTest::new,
        Featurekey.CREE_RENDEZ_VOUS, CreeRendezVous::new,
            Featurekey.ADD_SECRETAIRE, AddSecretaire::new
    );


    public static BehaviorPack build(Offre offre) {
        try {
            BehaviorPack behaviorPack = new BehaviorPack();
             for (Featurekey key : offre.getFeaturekeys()) {
                Supplier<Functionnalitie> supplier = registry.get(key);
                if (supplier == null) {
                   throw new FeatureKeyHasNoFeactionnalityError("No Functionnalitie registered for key: " + key);
                } 
                 behaviorPack.addFeature(key, supplier.get());
            }
            return behaviorPack;
        } catch (Exception e) {
            throw new RuntimeException("Error building BehaviorPack: " + e.getMessage(), e);
        }
           
        
    }
}
