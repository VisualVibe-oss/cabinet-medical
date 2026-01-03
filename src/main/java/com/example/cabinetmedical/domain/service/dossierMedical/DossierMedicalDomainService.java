package com.example.cabinetmedical.domain.service.dossierMedical;

import com.example.cabinetmedical.domain.model.dossierMedical.DossierMedical;
import org.springframework.stereotype.Service;

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
