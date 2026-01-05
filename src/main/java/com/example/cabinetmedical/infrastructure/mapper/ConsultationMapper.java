package com.example.cabinetmedical.infrastructure.mapper;


import com.example.cabinetmedical.application.DTO.*;
import com.example.cabinetmedical.domain.model.consultation.Consultation;
import com.example.cabinetmedical.domain.model.Examen.Examen;
import com.example.cabinetmedical.domain.model.ordonnance.*;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.ExamenEntity;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import com.example.cabinetmedical.infrastructure.entity.MedciamentOrdonnanceEntity;
import com.example.cabinetmedical.infrastructure.entity.OrdonnanceMedEntity;
import com.example.cabinetmedical.infrastructure.entity.OrdonnanceSupEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class ConsultationMapper {

    public static Consultation  toDomain(RequestConsultationDTO dto) {
        if (dto == null) return null;

        Consultation consultation = new Consultation();
        
        // Mapping des champs simples
        consultation.setMotif(dto.getMotif());
        consultation.setSymptome(dto.getSymptome());
        consultation.setTemperature(dto.getTemperature());
        consultation.setDateSymptome(dto.getDateSymptome());
        consultation.setPoid(dto.getPoid());
        consultation.setTension(dto.getTension());
        consultation.setFrequenceCardiaque(dto.getFrequenceCardiaque());
        consultation.setObservationClinique(dto.getObservationClinique());

        // Initialisation des champs obligatoires du domaine non présents dans le DTO
        consultation.setDate(new Date()); // Par défaut date du jour

        // 1. Mapping des Examens
        if (dto.getExamenDemande() != null) {
            List<Examen> examens = dto.getExamenDemande().stream()
                    .map(examenDemandeDTO ->{
                        Examen examen = new Examen();
                        examen.setType(examenDemandeDTO.getType());
                        examen.setEtat(examenDemandeDTO.getEtat());
                        return examen;
                    })
                    .collect(Collectors.toList());
            consultation.setExamens(examens);
        }

        // 2. Mapping de l'Ordonnance
        if (dto.getOrdonnance() != null) {
            // Mapping vers OrdonnanceSup
            OrdonnanceDTO ordonnanceDTO = dto.getOrdonnance() ;
            OrdonnanceSup ordonnanceSup = new OrdonnanceSup();

            ordonnanceSup.setArretDebut(ordonnanceDTO.getArretDebut());
            ordonnanceSup.setArretFin(ordonnanceDTO.getArretFin());
            ordonnanceSup.setArretTravail(ordonnanceDTO.getArretTravail());
            ordonnanceSup.setRegime(ordonnanceDTO.getRegime());
            ordonnanceSup.setReposStrict(ordonnanceDTO.getReposStrict());

            consultation.setOrdonnanceSup(ordonnanceSup);
            
            // Mapping vers OrdonnanceMed
            OrdonnanceMed ordonnanceMed = new OrdonnanceMed();
            if (ordonnanceDTO.getMedicament() != null) {
                List<MedicamentOrdonnance> medicaments = ordonnanceDTO.getMedicament().stream()
                        .map(medicamentDTO -> {
                            MedicamentOrdonnance medicament = new MedicamentOrdonnance();
                            medicament.setNomMedicament(medicamentDTO.getNom());
                            medicament.setDosage(medicamentDTO.getDosage());
                            medicament.setDuration(medicamentDTO.getDuration());
                            return medicament;
                        })
                        .collect(Collectors.toList());
                ordonnanceMed.setMedicaments(medicaments);
                consultation.setOrdonnanceMed(ordonnanceMed);
            }

            
        }

        return consultation;
    }





    public ConsultationEntity toEntity(Consultation domain) {
    if (domain == null) return null;

    ConsultationEntity entity = new ConsultationEntity();

    // 1. Champs de base
    entity.setIdConsultation(domain.getIdConsultation());
    entity.setType(domain.getType());
    entity.setMotif(domain.getMotif());
    entity.setSymptome(domain.getSymptome());
    entity.setTemperature(domain.getTemperature());
    entity.setDateSymptome(domain.getDateSymptome());
    entity.setPoid(domain.getPoid());
    entity.setTension(domain.getTension());
    entity.setFrequenceCardiaque(domain.getFrequenceCardiaque());
    entity.setObservationClinique(domain.getObservationClinique());
    entity.setDate(domain.getDate());

    // 2. Gestion de l'Ordonnance Médicale
    if (domain.getOrdonnanceMed() != null) {
        OrdonnanceMedEntity medEntity = new OrdonnanceMedEntity();
        medEntity.setIdOrdonnanceMed(domain.getOrdonnanceMed().getIdOrdonnanceMed());
        
        // LIEN PARENT : Crucial pour mappedBy dans OrdonnanceMedEntity
        medEntity.setConsultation(entity); 

        if (domain.getOrdonnanceMed().getMedicaments() != null) {
            medEntity.setMedicaments(domain.getOrdonnanceMed().getMedicaments().stream()
                .map(mDom -> {
                    MedciamentOrdonnanceEntity mEnt = new MedciamentOrdonnanceEntity();
                    mEnt.setDosage(mDom.getDosage());
                    mEnt.setDuration(mDom.getDuration());
                    
                    // LIEN PARENT : Chaque médicament doit connaître son ordonnance
                    mEnt.setOrdonnanceMed(medEntity); 
                    return mEnt;
                }).collect(Collectors.toList()));
        }
        entity.setOrdonnanceMed(medEntity);
    }

    // 3. Gestion de l'Ordonnance Supplémentaire
    if (domain.getOrdonnanceSup() != null) {
        OrdonnanceSupEntity supEntity = new OrdonnanceSupEntity();
        supEntity.setIdOrdonnanceSup(domain.getOrdonnanceSup().getIdOrdonnanceSup());
        supEntity.setRegime(domain.getOrdonnanceSup().getRegime());
        supEntity.setReposStrict(domain.getOrdonnanceSup().getReposStrict());
        supEntity.setArretTravail(domain.getOrdonnanceSup().getArretTravail());
        supEntity.setArretDebut(domain.getOrdonnanceSup().getArretDebut());
        supEntity.setArretFin(domain.getOrdonnanceSup().getArretFin());
        
        // LIEN PARENT : Même si c'est un JoinColumn, c'est une bonne pratique
        supEntity.setConsultation(entity); 
        
        entity.setOrdonnanceSup(supEntity);
    }

    // 4. Gestion de la Facture
    if (domain.getFacture() != null) {
        FactureEntity factureEntity = new FactureEntity();
        factureEntity.setIdFacture(domain.getFacture().getIdFacture());
        factureEntity.setType(domain.getFacture().getType());
        factureEntity.setPrix(domain.getFacture().getPrix());
        factureEntity.setDate(domain.getFacture().getDate());
        factureEntity.setState(domain.getFacture().getState());
        
        // LIEN PARENT : Indispensable car FactureEntity a le mappedBy="consultation"
        factureEntity.setConsultation(entity);
        
        entity.setFacture(factureEntity);
    }

    // 5. Gestion des Examens
    if (domain.getExamens() != null) {
        entity.setExamenEntities(domain.getExamens().stream()
                .map(exDom -> {
                    ExamenEntity exEnt = new ExamenEntity();
                    exEnt.setType(exDom.getType());
                    exEnt.setEtat(exDom.getEtat());

                    if (exDom.getFile() != null && exDom.getFile().contains(",")) {
                        String cleanBase64 = exDom.getFile().split(",")[1];
                        byte[] binaire = Base64.getDecoder().decode(cleanBase64);
                        exEnt.setFile(binaire);
                    }
                    
                    // LIEN PARENT : Indispensable pour la FK id_consultation dans la table examen
                    exEnt.setConsultation(entity); 
                    
                    return exEnt;
                }).collect(Collectors.toList()));
    }

    return entity;
}

    private Examen mapExamen(ExamenDemandeDTO dto) {
        if (dto == null) return null;
        Examen examen = new Examen();
        examen.setType(dto.getType());
        examen.setEtat(dto.getEtat());
        return examen;
    }

    private OrdonnanceSup mapOrdonnanceSup(OrdonnanceDTO dto) {
        if (dto == null) return null;
        OrdonnanceSup sup = new OrdonnanceSup();
        sup.setReposStrict(dto.getReposStrict());
        sup.setRegime(dto.getRegime());
        sup.setArretTravail(dto.getArretTravail());
        sup.setArretDebut(dto.getArretDebut());
        sup.setArretFin(dto.getArretFin());
        return sup;
    }

    private OrdonnanceMed mapOrdonnanceMed(OrdonnanceDTO dto) {
        if (dto == null || dto.getMedicament() == null) return null;
        
        OrdonnanceMed med = new OrdonnanceMed();
        List<MedicamentOrdonnance> list = dto.getMedicament().stream()
                .map(mDto -> {
                    MedicamentOrdonnance mDom = new MedicamentOrdonnance();
                    mDom.setNomMedicament(mDto.getNom());
                    mDom.setDosage(mDto.getDosage());
                    mDom.setDuration(mDto.getDuration());
                    return mDom;
                })
                .collect(Collectors.toList());
        
        med.setMedicaments(list);
        return med;
    }
}
