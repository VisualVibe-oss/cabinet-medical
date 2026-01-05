package com.example.cabinetmedical.domain.permissions.dossier;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.dossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.service.dossierMedical.DossierMedicalDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

import java.time.LocalDateTime;
import java.util.Date;

public class CreeDossierMedical implements ExecutePermission<DossierMedical, DossierMedical> {

    private final DossierMedicalDomainService dossierMedicalDomainService;

    public CreeDossierMedical(DossierMedicalDomainService dossierMedicalDomainService) {
        this.dossierMedicalDomainService = dossierMedicalDomainService;
    }

    @Override
    public PermissionResponce<DossierMedical> doWork(PermissionParameter<DossierMedical> param) {
        DossierMedical dossier = param.getPayload();

        // ✅ Validation via service si vous en avez une
        // dossierMedicalDomainService. validateDossierData(dossier);

        // ✅ Initialisation des champs vides
        if (dossier.getAntMedicaux() == null) dossier.setAntMedicaux("");
        if (dossier. getAntChirug() == null) dossier.setAntChirug("");
        if (dossier.getAllergies() == null) dossier.setAllergies("");
        if (dossier.getTraitement() == null) dossier.setTraitement("");
        if (dossier.getHabitudes() == null) dossier.setHabitudes("");
        if (dossier.getHistoriqueConsultations() == null) dossier.setHistoriqueConsultations("");
        if (dossier.getDocumentsMedicaux() == null) dossier.setDocumentsMedicaux("");

        // ✅ Date de création
        dossier.setDateCreation(new Date());

        return new PermissionResponce<>(PermissionKey.CREE_DOSSIER_MEDICAL, dossier);
    }
}