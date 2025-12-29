package com.example.cabinetmedical.domain.model.behaviorPackBuilder;

import com.example.cabinetmedical.domain.model.Medecin.DeleteSecretaire;
import com.example.cabinetmedical.domain.model.Medecin.EditSecretaire;
import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.Medecin.AddSecretaire;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.functionnalities.FunctionnalitieTest;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.exception.FeatureKeyHasNoFeactionnalityError;
import com.example.cabinetmedical.exception.PackNotRegistredError;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class BehaviorPackBuilder {
    
    //* registry contient les associations entre les Featurekey et les fournisseurs de Functionnalitie correspondants */
   private static Map<Featurekey, Supplier<Functionnalitie>> featureRegistry = Map.of(
        Featurekey.TEST, FunctionnalitieTest::new,
            Featurekey.ADD_SECRETAIRE, AddSecretaire::new,
            Featurekey.EDIT_SECRETAIRE, EditSecretaire::new,
            Featurekey.DELETE_SECRETAIRE, DeleteSecretaire::new
    );


    private static Map<PackKey, List<Featurekey>> packRegistry = Map.of(
        PackKey.Pack_TEST, List.of(Featurekey.TEST  )
    );


    public static BehaviorPack build(Offre offre) {

        BehaviorPack behaviorPack = new BehaviorPack();
        PackKey packKey = offre.getPackkey();
        List<Featurekey> featureList = packRegistry.get(packKey);
        if (featureList == null) {
            throw new PackNotRegistredError("Cette pack n'a acune feature encore");
        }
        for (Featurekey key : featureList) {
            Supplier<Functionnalitie> supplier = featureRegistry.get(key);
            if (supplier == null) {
                throw new FeatureKeyHasNoFeactionnalityError("No Functionnalitie registered for key: " + key);
            }
            behaviorPack.addFeature(key, supplier.get());
        }
        return behaviorPack;

    }
}
