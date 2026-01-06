package com.example.cabinetmedical.domain.service.patient;

import com.example.cabinetmedical.domain.model.patient.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientDomainService {
    public void validatePatientData(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être null");
        }

        if (patient. getNom() == null || patient. getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du patient est obligatoire");
        }

        if (patient.getPrenom() == null || patient.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom du patient est obligatoire");
        }

        if (patient.getCin() == null || patient.getCin().trim().isEmpty()) {
            throw new IllegalArgumentException("Le CIN du patient est obligatoire");
        }

        if (patient.getTelephone() == null || patient.getTelephone().trim().isEmpty()) {
            throw new IllegalArgumentException("Le téléphone du patient est obligatoire");
        }

        if (patient.getSexe() == null || patient.getSexe().trim().isEmpty()) {
            throw new IllegalArgumentException("Le sexe du patient est obligatoire");
        }


        if (patient.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }

    }

    public void validateTelephoneFormat(String telephone) {
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }

        if (! telephone.matches("^[0-9+\\-\\s()]+$")) {
            throw new IllegalArgumentException("Format de téléphone invalide");
        }
    }
}
