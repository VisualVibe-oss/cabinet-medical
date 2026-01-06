package com.example.cabinetmedical.domain.permissions.patient;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.service.patient.PatientDomainService;
import com. example.cabinetmedical. domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils. PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class CreerPatient implements ExecutePermission<Patient, Patient> {

    private final PatientDomainService patientDomainService;

    public CreerPatient(PatientDomainService patientDomainService) {
        this.patientDomainService = patientDomainService;
    }

    @Override
    public PermissionResponce<Patient> doWork(PermissionParameter<Patient> param) {
        Patient patient = param.getPayload();

        // ✅ Validation via le service de domaine
        patientDomainService.validatePatientData(patient);
        patientDomainService.validateTelephoneFormat(patient.getTelephone());

        // ✅ Normalisation des données
        patient.setNom(patient.getNom().trim().toUpperCase());
        patient.setPrenom(patient.getPrenom().trim());
        patient.setCin(patient.getCin().trim().toUpperCase());
        patient.setTelephone(patient.getTelephone().trim());

        // ✅ Valeur par défaut pour la mutuelle
        if (patient.getTypeMutuelle() == null || patient.getTypeMutuelle().trim().isEmpty()) {
            patient. setTypeMutuelle("AUCUNE");
        }

        return new PermissionResponce<>(PermissionKey.CREE_PATIENT, patient);
    }
}
