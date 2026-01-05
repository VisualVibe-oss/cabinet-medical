package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.DTO.MedecinDTO;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedecinMapper {
    CabinetMapper cm ;

    public  static Medecin toDomain(MedecinDTO mdto) {
        Medecin medecin = new Medecin(mdto.getIdMedecin(), mdto.getNom(), mdto.getPrenom(), mdto.getEmail(), mdto.getPassword(), mdto.getTelephone(), mdto.getCabinet());
        return medecin;
    }






    public static Medecin toDomain(MedecinEntity me) {
    if (me == null) {
        return null;
    }

    Medecin medecin = new Medecin();
    
    // Utilisation des Setters pour les types simples
    medecin.setIdMedecin(me.getIdMedecin());
    medecin.setNom(me.getNom());
    medecin.setPrenom(me.getPrenom());
    medecin.setEmail(me.getEmail());
    medecin.setPassword(me.getPassword());
    medecin.setTelephone(me.getTelephone());

    // Vérification de nullité pour l'objet complexe Cabinet
    if (me.getCabinet() != null) {
        // On suppose que 'cm' est votre CabinetMapper
        medecin.setCabinet(CabinetMapper.toDomain(me.getCabinet()));
         
    } else {
        medecin.setCabinet(null);
    }

    
    return medecin;
}
public static List<Medecin> toDomainList(List<MedecinEntity> me){
return me.stream().map(MedecinMapper::toDomain).toList();
}

}
