package com.example.cabinetmedical.domain.model.behaviorPackBuilder;

import com.example.cabinetmedical.domain.model.Medecin.*;
import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.model.Stats.StatsFunctionality;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.functionnalities.CreerConsultation;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.functionnalities.FunctionnalitieTest;
import com.example.cabinetmedical.domain.model.functionnalities.GetRDVInfo;
import com.example.cabinetmedical.domain.model.functionnalities.GetRendezVous;
import com.example.cabinetmedical.domain.model.functionnalities.SecretaryChecks.CheckAddRendezVous;
import com.example.cabinetmedical.domain.model.functionnalities.SetStateRdvOngoing;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.exception.FeatureKeyHasNoFeactionnalityError;
import com.example.cabinetmedical.exception.PackNotRegistredError;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class BehaviorPackBuilder {
    
    // * registry contient les associations entre les Featurekey et les fournisseurs
    // de Functionnalitie correspondants */
    private static Map<Featurekey, Supplier<Functionnalitie>> featureRegistry = Map.of(
            Featurekey.EDIT_SECRETAIRE, EditSecretaire::new,
            Featurekey.DELETE_SECRETAIRE, DeleteSecretaire::new,
            Featurekey.EDIT_DEPENCE, EditDepence::new,
            Featurekey.ADD_DEPENCE, AddDepence::new  ,
            Featurekey.ADD_SECRETAIRE , AddSecretaire::new ,
            Featurekey.DELETE_DEPENCE , DeleteDepence::new,
            Featurekey.EDIT_RENDEZ_VOUS , EditSecretaire::new,
            Featurekey.VIEW_STATS , StatsFunctionality::new,
            Featurekey.CREE_CONSULTATION, CreerConsultation::new

    );


    private static Map<PackKey, List<Featurekey>> packRegistry = Map.of(
        PackKey.Pack_TEST, List.of(Featurekey.TEST  ),
        PackKey.BASIC , List.of(
                    Featurekey.EDIT_SECRETAIRE,
                    Featurekey.ADD_DEPENCE,
                    Featurekey.EDIT_RENDEZ_VOUS,
                    Featurekey.VIEW_STATS,
                    Featurekey.EDIT_DEPENCE,
                    Featurekey.CREE_CONSULTATION,
                    Featurekey.DELETE_SECRETAIRE,
                    Featurekey.ADD_SECRETAIRE,
                    Featurekey.DELETE_DEPENCE
        )
    );

    public static List<Featurekey> getFeaturesForPack(PackKey packKey) {
        List<Featurekey> features = packRegistry.get(packKey);
        if (features == null) {
            throw new PackNotRegistredError("Cette pack n'a aucune feature");
        }
        return features;
    }



    public static BehaviorPack build(Offre offre) {

        BehaviorPack behaviorPack = new BehaviorPack();
        PackKey packKey = offre.getPackKey();
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
