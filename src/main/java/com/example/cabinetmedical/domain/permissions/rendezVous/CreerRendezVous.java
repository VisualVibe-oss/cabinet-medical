package com.example.cabinetmedical.domain.permissions.rendezVous;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.rendezVous.RendezVous;
import com.example.cabinetmedical.domain.service.rendezVous.RendezVousDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class CreerRendezVous implements ExecutePermission<RendezVous, RendezVous> {

    private final RendezVousDomainService rendezVousDomainService;

    public CreerRendezVous(RendezVousDomainService rendezVousDomainService) {
        this.rendezVousDomainService = rendezVousDomainService;
    }

    @Override
    public PermissionResponce<RendezVous> doWork(PermissionParameter<RendezVous> param) {
        RendezVous rendezVous = param. getPayload();

        // ✅ Validation via le service de domaine
        rendezVousDomainService.validerCreation(rendezVous);

        // ✅ Définir le statut par défaut si non fourni
        if (rendezVous.getStatut() == null || rendezVous.getStatut().trim().isEmpty()) {
            rendezVous.setStatut("Scheduled");
        }

        // ✅ Initialiser les notes si vides
        if (rendezVous.getNotes() == null) {
            rendezVous. setNotes("");
        }

        // ✅ Initialiser le motif si vide
        if (rendezVous.getMotif() == null) {
            rendezVous.setMotif("");
        }

        return new PermissionResponce<>(PermissionKey.CREER_RENDEZ_VOUS, rendezVous);
    }
}