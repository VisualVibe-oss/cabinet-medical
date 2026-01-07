package com.example.cabinetmedical.domain.permissions.rendezVous;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.service.rendezVous.RendezVousDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class ModifierRendezVous implements ExecutePermission<RendezVous, RendezVous> {

    private final RendezVousDomainService rendezVousDomainService;

    public ModifierRendezVous(RendezVousDomainService rendezVousDomainService) {
        this.rendezVousDomainService = rendezVousDomainService;
    }

    @Override
    public PermissionResponce<RendezVous> doWork(PermissionParameter<RendezVous> param) {
        RendezVous rendezVous = param.getPayload();

        // ✅ Vérification spécifique à la modification
        if (rendezVous.getIdRendezVous() <= 0) {
            throw new IllegalArgumentException("L'ID du rendez-vous est requis pour la modification");
        }

        return new PermissionResponce<>(PermissionKey.MODIFIER_RENDEZ_VOUS, rendezVous);
    }
}