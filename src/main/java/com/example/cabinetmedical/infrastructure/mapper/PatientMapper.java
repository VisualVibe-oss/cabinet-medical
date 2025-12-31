package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public static PatientMapper(CabinetMapper cm) {
        this.cm = cm;
    }

    public PatientEntity toEntity(Patient p) {
        PatientEntity pe = new PatientEntity();

        List<RendezVousEntity> rendezVousEntities = null;
        if (p.getRendezVous() != null) {
            rendezVousEntities = p.getRendezVous()
                    .stream()
                    .map(rvm::toEntity)
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

    public Patient toDomain(PatientEntity pe){


        Patient p = new Patient();

        List<RendezVous> rendezVousDomains = null;
        if (pe.getRendezVous() != null) {
            rendezVousDomains = pe.getRendezVous()
                    .stream()
                    .map(rvm::toDomain)
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
        p.setCabinet(CabinetMapper .toDomain(pe.getCabinet()));
        p.setRendezVous(rendezVousDomains);

        return p;
    }
}
