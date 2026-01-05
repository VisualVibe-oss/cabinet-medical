package com.example.cabinetmedical.domain.permissions.rendezVous;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class SupprimerRendezVous implements ExecutePermission<Long, Long> {

    @Override
    public PermissionResponce<Long> doWork(PermissionParameter<Long> param) {
        Long rendezVousId = param.getPayload();

        // ✅ Validation
        if (rendezVousId == null || rendezVousId <= 0) {
            throw new IllegalArgumentException("L'ID du rendez-vous est invalide");
        }

        // ✅ Règle métier : Ne permettre la suppression que des rendez-vous futurs
        // (À implémenter dans le service si nécessaire)

        return new PermissionResponce<>(PermissionKey.SUPPRIMER_RENDEZ_VOUS, rendezVousId);
    }
}