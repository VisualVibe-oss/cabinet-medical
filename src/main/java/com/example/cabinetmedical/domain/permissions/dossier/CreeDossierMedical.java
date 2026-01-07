package com.example.cabinetmedical.domain.permissions.dossier;

import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
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

        // ✅ Date de création
        dossier.setDateCreation(new Date());

        return new PermissionResponce<>(PermissionKey.CREE_DOSSIER_MEDICAL, dossier);
    }
}