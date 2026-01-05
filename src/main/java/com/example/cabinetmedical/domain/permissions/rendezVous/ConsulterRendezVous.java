package com.example.cabinetmedical.domain.permissions.rendezVous;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class ConsulterRendezVous implements ExecutePermission<Long, Long> {

    @Override
    public PermissionResponce<Long> doWork(PermissionParameter<Long> param) {
        Long rendezVousId = param.getPayload();

        // ✅ Validation simple
        if (rendezVousId == null || rendezVousId <= 0) {
            throw new IllegalArgumentException("L'ID du rendez-vous est invalide");
        }

        // ✅ Pas de règle métier particulière pour la consultation
        // C'est juste une vérification d'accès

        return new PermissionResponce<>(PermissionKey.CONSULTER_RENDEZ_VOUS, rendezVousId);
    }
}