package com.example.cabinetmedical.config.startScripts;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.infrastructure.entity.OffreEntity;
import com.example.cabinetmedical.infrastructure.repository.OffreRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;

@Component
@Order(1) // S'exécute en premier
public class SynchronizeOffre implements ApplicationRunner {

   @Autowired
   private OffreRepository offreRepository;

   @Autowired
   private CabinetRepository cabinetRepository;

   private List <OffreEntity> offreEntities   = List.of(
        new OffreEntity(30,PackKey.Pack_TEST,"test",29.99f,"Offre basique pour les petits cabinets",List.of()) ,
        new OffreEntity(30,PackKey.BASIC,"Basic",29.99f,"Offre basique pour les petits cabinets",List.of()) 
    );

    private void verifyUniquePackKey() {
        for (int i = 0; i < offreEntities.size(); i++) {
            for (int j = i + 1; j < offreEntities.size(); j++) {
                if (offreEntities.get(i).getKey() == offreEntities.get(j).getKey()) {
                    throw new IllegalArgumentException(
                        "Deux offres ont le même PackKey: " + offreEntities.get(i).getKey()
                    );
                }
            }
        }
    }

   private void synchronizeOffreWithDatabase() {
    for (OffreEntity offre : offreEntities) {
        Optional<OffreEntity> existingOffre = offreRepository.findByPackKey(offre.getKey());

        if (existingOffre.isPresent()) {
            OffreEntity toUpdate = existingOffre.get();
            // mettre à jour les champs directement
            toUpdate.setType(offre.getType());
            toUpdate.setPrix(offre.getPrix());
            toUpdate.setDescription(offre.getDescription());
            // NE PAS toucher à l'id ni à la version
            offreRepository.saveAndFlush(toUpdate); // flush immédiat pour éviter conflit
        } else {
            offreRepository.save(offre);
        }
    }
}

    private void deleteUnusedOffres() {
        // Récupérer toutes les offres de la base de données
        List<OffreEntity> allOffresInDb = offreRepository.findAll();
        
        for (OffreEntity offreInDb : allOffresInDb) {
            // Vérifier si cette offre existe dans la liste
            boolean exists = offreEntities.stream()
                .anyMatch(offre -> offre.getKey() == offreInDb.getKey());
            
            if (!exists) {
                // Vérifier et mettre à null les relations avec les cabinets
                cabinetRepository.nullifyOffreRelation(offreInDb.getIdOffre());
                
                //rendre la date du fin offre null 
                cabinetRepository.setOffreEndDateToNullByOffreId(offreInDb.getIdOffre());
                // Supprimer l'offre de la base de données
                offreRepository.deleteById(offreInDb.getIdOffre());
            }
        }
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        verifyUniquePackKey();
        synchronizeOffreWithDatabase();
        deleteUnusedOffres();
    }
}
