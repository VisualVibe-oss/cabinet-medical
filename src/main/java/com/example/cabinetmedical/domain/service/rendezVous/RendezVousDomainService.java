package com.example.cabinetmedical.domain.service.rendezVous;

import org.springframework.stereotype.Service;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class RendezVousDomainService {
    public void validerCreation(RendezVous rendezVous) {
        // Vérifier que l'objet n'est pas null
        if (rendezVous == null) {
            throw new IllegalArgumentException("Le rendez-vous ne peut pas être null");
        }

        // Vérifier la date
        if (rendezVous. getDateDebutRendezVous() == null) {
            throw new IllegalArgumentException("La date du rendez-vous est obligatoire");
        }

if (rendezVous.getDateDebutRendezVous().toLocalDate().atStartOfDay().isBefore(LocalDateTime.now())){ 
            throw new IllegalArgumentException("La date du rendez-vous ne peut pas être dans le passé");
        }

        // Vérifier le motif
        if (rendezVous.getMotif() == null || rendezVous.getMotif().trim().isEmpty()) {
            throw new IllegalArgumentException("Le motif est obligatoire");
        }

        // Vérifier le patient
        if (rendezVous.getPatient() == null) {
            throw new IllegalArgumentException("Le patient est obligatoire");
        }

        // Vérifier le cabinet
        if (rendezVous.getCabinet() == null) {
            throw new IllegalArgumentException("Le cabinet est obligatoire");
        }

        
    }
}
