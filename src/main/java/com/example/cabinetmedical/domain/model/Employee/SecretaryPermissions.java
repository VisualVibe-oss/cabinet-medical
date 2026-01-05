package com.example.cabinetmedical.domain.model.Employee;

import com.example.cabinetmedical.domain.model.secretaire.CreeRendezVous;
import com.example.cabinetmedical.domain.permissions.dossier.CreeDossierMedical;
import com.example.cabinetmedical.domain.permissions.dossier.ModifierDossierMedical;
import com.example.cabinetmedical.domain.permissions.patient.CreerPatient;
import com.example.cabinetmedical.domain.permissions.patient.ModifierPatient;
import com.example.cabinetmedical.domain.permissions.patient.SupprimerPatient;
import com.example.cabinetmedical.domain.permissions.rendezVous.ConsulterRendezVous;
import com.example.cabinetmedical.domain.permissions.rendezVous.CreerRendezVous;
import com.example.cabinetmedical.domain.permissions.rendezVous.ModifierRendezVous;
import com.example.cabinetmedical.domain.permissions.rendezVous.SupprimerRendezVous;
import com.example.cabinetmedical.domain.service.dossierMedical.DossierMedicalDomainService;
import com.example.cabinetmedical.domain.service.patient.PatientDomainService;
import com.example.cabinetmedical.domain.service.rendezVous.RendezVousDomainService;
import com.example.cabinetmedical.domain.utils.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SecretaryPermissions {
    private final Map<PermissionKey, ExecutePermission> registry;

    public SecretaryPermissions(
            PatientDomainService patientDomainService,
            DossierMedicalDomainService dossierMedicalDomainService,
            RendezVousDomainService rendezVousDomainService) {  // ✅ Ajout
        this.registry = createFullRegistry(
                patientDomainService,
                dossierMedicalDomainService,
                rendezVousDomainService  // ✅ Ajout
        );
    }

    private Map<PermissionKey, ExecutePermission> createFullRegistry(
            PatientDomainService patientDomainService,
            DossierMedicalDomainService dossierMedicalDomainService,
            RendezVousDomainService rendezVousDomainService) {  // ✅ Ajout

        Map<PermissionKey, ExecutePermission> map = new HashMap<>();

        // ✅ Permissions patients
        map.put(PermissionKey.CREE_PATIENT,
                new CreerPatient(patientDomainService));
        map.put(PermissionKey.MODIFIER_PATIENT,
                new ModifierPatient(patientDomainService));
        map.put(PermissionKey.SUPPRIMER_PATIENT,
                new SupprimerPatient());

        // ✅ Permissions dossiers médicaux
        map.put(PermissionKey.CREE_DOSSIER_MEDICAL,
                new CreeDossierMedical(dossierMedicalDomainService));
        map.put(PermissionKey.MODIFIER_DOSSIER_MEDICAL,
                new ModifierDossierMedical(dossierMedicalDomainService));

        // ✅ Permissions rendez-vous
        map. put(PermissionKey. CREER_RENDEZ_VOUS,
                new CreerRendezVous(rendezVousDomainService));
        map.put(PermissionKey.MODIFIER_RENDEZ_VOUS,
                new ModifierRendezVous(rendezVousDomainService));
        map.put(PermissionKey.SUPPRIMER_RENDEZ_VOUS,
                new SupprimerRendezVous());
        map.put(PermissionKey.CONSULTER_RENDEZ_VOUS,
                new ConsulterRendezVous());

        return map;
    }

    public PermissionResponce<? > doWork(PermissionParameter<?> param) {
        if (! registry.containsKey(param. getKey())) {
            throw new IllegalStateException(
                    "Permission non implémentée : " + param.getKey()
            );
        }
        return registry.get(param.getKey()).doWork(param);
    }

    public boolean isPermissionImplemented(PermissionKey key) {
        return registry.containsKey(key);
    }
}
