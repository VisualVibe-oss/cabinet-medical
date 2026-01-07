package com.example.cabinetmedical.domain.permissions.dossier;


import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.service.dossierMedical.DossierMedicalDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class ModifierDossierMedical implements ExecutePermission<DossierMedical, DossierMedical> {

    private final DossierMedicalDomainService dossierMedicalDomainService;

    public ModifierDossierMedical(DossierMedicalDomainService dossierMedicalDomainService) {
        this.dossierMedicalDomainService = dossierMedicalDomainService;
    }

    @Override
    public PermissionResponce<DossierMedical> doWork(PermissionParameter<DossierMedical> param) {
        DossierMedical dossier = param.getPayload();

        // ✅ Vérification que l'ID existe
        if (dossier. getIdDossier() == null || dossier.getIdDossier() <= 0) {
            throw new IllegalArgumentException("L'ID du dossier médical est requis pour la modification");
        }

        // ✅ Validation si nécessaire
        // dossierMedicalDomainService.validateDossierData(dossier);

        return new PermissionResponce<>(PermissionKey.MODIFIER_DOSSIER_MEDICAL, dossier);
    }
}
