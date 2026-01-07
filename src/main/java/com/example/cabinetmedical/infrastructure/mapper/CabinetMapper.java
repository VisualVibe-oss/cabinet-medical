package com.example.cabinetmedical.infrastructure.mapper;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.application.DTO.CabinetDTO;
import com.example.cabinetmedical.application.DTO.MedecinDTO;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


/**
 * Mapper utility pour créer un CabinetEntity à partir de MedecinDTO et CabinetDTO.
 * Adaptez les noms de getters/setters si vos classes ont des noms différents.
 */
@Component
public final class CabinetMapper {

    public CabinetMapper() {}

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
            if (medecinDTO.getTelephone() != null) med.setTelephone(medecinDTO.getTelephone());
            if(medecinDTO.getPassword() !=null) med.setPassword(medecinDTO.getPassword()) ;

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
  


    public static Cabinet toDomain(CabinetEntity entity) {
        if (entity == null) {
            return null;
        }

        Cabinet domain = new Cabinet();
        
        // Mappage des champs simples
        domain.setIdCabinet(entity.getIdCabinet());
        domain.setNom(entity.getNom());
        domain.setSpecialite(entity.getSpecialite());
        domain.setAdresse(entity.getAdresse());
        domain.setEtat(entity.getEtat());
        // Note: L'entité n'a pas de champ 'telephone', mais le domaine oui. 
        // L'entité a 'signatureBase64' qui pourrait correspondre au 'logo'.
        domain.setSignatureBase64(entity.getSignatureBase64()); 
        domain.setLogo(entity.getLogo());

        
        if (entity.getOffre() != null) {
            domain.setOffre(OffreMapper.entityToDomain(entity.getOffre()));
        }

        

        return domain;
    }



     public static  CabinetEntity toEntity(Cabinet domain) {
        if (domain == null) {
            return null;
        }

        CabinetEntity entity = new CabinetEntity();
        entity.setIdCabinet(domain.getIdCabinet());
        entity.setLogo(domain.getLogo());
        entity.setNom(domain.getNom());
        entity.setSpecialite(domain.getSpecialite());
        entity.setAdresse(domain. getAdresse());
        entity.setEtat(domain.getEtat());

        return entity;
    }



    public static  Cabinet toDomain(CabinetDTO cabinetDTO  ){
        Cabinet cabinet = new Cabinet() ; 

        cabinet.setIdCabinet(cabinetDTO.getIdCabinet());
        cabinet.setLogo(cabinetDTO.getLogo());
        cabinet.setNom(cabinetDTO.getNom());
        cabinet.setSpecialite(cabinetDTO.getSpecialite());
        cabinet.setAdresse(cabinetDTO. getAdresse());
        cabinet.setEtat(cabinetDTO.isEtat());      
        
        return cabinet ;
    }


    
    
}





