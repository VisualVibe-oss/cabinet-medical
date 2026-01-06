package com.example.cabinetmedical.domain.permissions.patient;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class SupprimerPatient implements ExecutePermission<Long, Long> {

    @Override
    public PermissionResponce<Long> doWork(PermissionParameter<Long> param) {
        Long patientId = param.getPayload();

        // ✅ Validation
        if (patientId == null || patientId <= 0) {
            throw new IllegalArgumentException("L'ID du patient est invalide");
        }

        // ✅ Logique métier supplémentaire si nécessaire
        // Par exemple : vérifier s'il a des rendez-vous futurs

        return new PermissionResponce<>(PermissionKey. SUPPRIMER_PATIENT, patientId);
    }
}
