package com.example.cabinetmedical.domain.service.dossierMedical;

import org.springframework.stereotype.Service;

import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;

@Service
public class DossierMedicalDomainService {

    public void validateDossierMedical(DossierMedical dossier) {
        if (dossier == null) {
            throw new IllegalArgumentException("Le dossier médical ne peut pas être null");
        }
        if (dossier. getDateCreation() == null) {
            throw new IllegalArgumentException("La date de création est obligatoire");
        }
    }

}
