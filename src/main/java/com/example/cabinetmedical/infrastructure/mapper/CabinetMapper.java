package com.example.cabinetmedical.infrastructure.mapper;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;

import com.example.cabinetmedical.application.DTO.CabinetDTO;
import com.example.cabinetmedical.application.DTO.MedecinDTO;


import org.springframework.stereotype.Component;


/**
 * Mapper utility pour créer un CabinetEntity à partir de MedecinDTO et CabinetDTO.
 * Adaptez les noms de getters/setters si vos classes ont des noms différents.
 */
@Component
public final class CabinetMapper {

    private CabinetMapper() {}

    public static CabinetEntity DtoToEntity(MedecinDTO medecinDTO, CabinetDTO cabinetDTO) {
        CabinetEntity cabinet = new CabinetEntity();

        if (cabinetDTO != null) {
            if (cabinetDTO.getNom() != null) cabinet.setNom(cabinetDTO.getNom());
            if (cabinetDTO.getAdresse() != null) cabinet.setAdresse(cabinetDTO.getAdresse());
            if (cabinetDTO.getPays() != null) cabinet.setPays(cabinetDTO.getPays());
            if( cabinetDTO.getSpecialite() != null) cabinet.setSpecialite(cabinetDTO.getSpecialite());
            if (cabinetDTO.getSignatureBase64() != null) cabinet.setSignatureBase64(cabinetDTO.getSignatureBase64());
        
        }

        if (medecinDTO != null) {
            MedecinEntity med = new MedecinEntity();
            if (medecinDTO.getNom() != null) med.setNom(medecinDTO.getNom());
            if (medecinDTO.getPrenom() != null) med.setPrenom(medecinDTO.getPrenom());
            if (medecinDTO.getEmail() != null) med.setEmail(medecinDTO.getEmail());
            if (medecinDTO.getNumero() != null) med.setTelephone(medecinDTO.getNumero());
            if(medecinDTO.getMotDePasse() !=null) med.setPassword(medecinDTO.getMotDePasse()) ;

            cabinet.setMedecin(med);
        }

        return cabinet;
    }

    private static boolean hasId(Object dto) {
        try {
            Object id = dto.getClass().getMethod("getId").invoke(dto);
            return id != null;
        } catch (Exception e) {
            return false;
        }
    }
  
  public Cabinet toDomain(CabinetEntity cabinetEntity) {
        Cabinet cabinet = new Cabinet();
        return cabinet;
    }
}





