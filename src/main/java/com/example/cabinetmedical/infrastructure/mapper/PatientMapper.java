package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.application.DTO.PatientDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
   

    public PatientMapper() {
     
    }

    public static PatientEntity toEntity(Patient p) {
        PatientEntity pe = new PatientEntity();

        List<RendezVousEntity> rendezVousEntities = null;
        if (p.getRendezVous() != null) {
            rendezVousEntities = p.getRendezVous()
                    .stream()
                    .map(RendezVousMapper::toEntity)
                    .collect(Collectors.toList());
        }

        pe.setIdPatient(p.getIdPatient());
        pe.setNom(p.getNom());
        pe.setPrenom(p.getPrenom());
        pe.setCin(p.getCin());
        pe.setPassword(p.getPassword());
        pe.setTelephone(p.getTelephone());
        pe.setSexe(p.getSexe());
        pe.setDateNaissance(p.getDateNaissance());
        pe.setTypeMutuelle(p.getTypeMutuelle());
        pe.setCabinet(CabinetMapper .toEntity(p.getCabinet()));
        pe.setRendezVous(rendezVousEntities);

        return pe;
    }

    public static Patient toDomain(PatientEntity pe){


        Patient p = new Patient();

        List<RendezVous> rendezVousDomains = null;
        if (pe.getRendezVous() != null) {
            rendezVousDomains = pe.getRendezVous()
                    .stream()
                    .map(RendezVousMapper::toDomain)
                    .collect(Collectors.toList());
        }
        p.setIdPatient(pe.getIdPatient());
        p.setNom(pe.getNom());
        p.setPrenom(pe.getPrenom());
        p.setCin(pe.getCin());
        p.setPassword(pe.getPassword());
        p.setTelephone(pe.getTelephone());
        p.setSexe(pe.getSexe());
        p.setDateNaissance(pe.getDateNaissance());
        p.setTypeMutuelle(pe.getTypeMutuelle());
        
        if (pe.getCabinet() != null) {
        p.setCabinet(CabinetMapper.toDomain(pe.getCabinet()));
    }

        
        return p;
    }



    public static PatientDTO toDTO(PatientEntity pe) {
        if (pe == null) {
            return null;
        }

        PatientDTO dto = new PatientDTO();
        
        // Mapping des champs simples
        dto.setIdPatient(pe.getIdPatient());
        dto.setNom(pe.getNom());
        dto.setPrenom(pe.getPrenom());
        dto.setCin(pe.getCin());
        dto.setTelephone(pe.getTelephone());
        dto.setSexe(pe.getSexe());
        dto.setDateNaissance(pe.getDateNaissance());
        dto.setTypeMutuelle(pe.getTypeMutuelle());

        

        return dto;
    }
    public static Patient toDomain(PatientDTO dto) {
        if (dto == null) return null;

        Patient p = new Patient();

        p.setIdPatient(dto.getIdPatient());
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setCin(dto.getCin());
        p.setTelephone(dto.getTelephone());
        p.setSexe(dto.getSexe());
        p.setDateNaissance(dto.getDateNaissance());
        p.setTypeMutuelle(dto.getTypeMutuelle());

        // Don't set rendezvous or cabinet unless needed (to avoid lazy-loading issues)
        return p;
    }
    public static PatientDTO toDTO(Patient p) {
        if (p == null) return null;

        PatientDTO dto = new PatientDTO();
        dto.setIdPatient(p.getIdPatient());
        dto.setNom(p.getNom());
        dto.setPrenom(p.getPrenom());
        dto.setCin(p.getCin());
        dto.setTelephone(p.getTelephone());
        dto.setSexe(p.getSexe());
        dto.setDateNaissance(p.getDateNaissance());
        dto.setTypeMutuelle(p.getTypeMutuelle());


        return dto;
    }
}
