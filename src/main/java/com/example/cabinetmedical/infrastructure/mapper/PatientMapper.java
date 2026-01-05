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

    private final CabinetMapper cm;

    public PatientMapper(CabinetMapper cm) {
        this.cm = cm;
    }

    public PatientEntity toEntity(Patient p) {
        PatientEntity pe = new PatientEntity();

        pe.setIdPatient(p.getIdPatient());
        pe.setNom(p.getNom());
        pe.setPrenom(p.getPrenom());
        pe.setCin(p.getCin());
        pe.setPassword(p.getPassword());
        pe.setTelephone(p.getTelephone());
        pe.setSexe(p.getSexe());
        pe.setDateNaissance(p.getDateNaissance());
        pe.setTypeMutuelle(p.getTypeMutuelle());
        pe.setCabinet(cm.toEntity(p.getCabinet()));

        // 🚫 DO NOT map rendezvous here
        pe.setRendezVous(null);

        return pe;
    }

    public Patient toDomain(PatientEntity pe) {
        Patient p = new Patient();

        p.setIdPatient(pe.getIdPatient());
        p.setNom(pe.getNom());
        p.setPrenom(pe.getPrenom());
        p.setCin(pe.getCin());
        p.setPassword(pe.getPassword());
        p.setTelephone(pe.getTelephone());
        p.setSexe(pe.getSexe());
        p.setDateNaissance(pe.getDateNaissance());
        p.setTypeMutuelle(pe.getTypeMutuelle());
        p.setCabinet(cm.toDomain(pe.getCabinet()));

        // 🚫 DO NOT map rendezvous here
        p.setRendezVous(null);

        return p;
    }
}
