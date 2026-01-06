package com.example.cabinetmedical.domain.permissions.patient;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.service.patient.PatientDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public class ModifierPatient implements ExecutePermission<Patient, Patient> {

    private final PatientDomainService patientDomainService;

    public ModifierPatient(PatientDomainService patientDomainService) {
        this.patientDomainService = patientDomainService;
    }

    @Override
    public PermissionResponce<Patient> doWork(PermissionParameter<Patient> param) {
        Patient patient = param.getPayload();

        // ✅ Vérification spécifique à la modification
        if (patient.getIdPatient() == null || patient.getIdPatient() <= 0) {
            throw new IllegalArgumentException("L'ID du patient est requis pour la modification");
        }

        // ✅ Validation via le service de domaine
        patientDomainService.validatePatientData(patient);
        patientDomainService.validateTelephoneFormat(patient.getTelephone());

        // ✅ Normalisation
        patient.setNom(patient.getNom().trim().toUpperCase());
        patient.setPrenom(patient.getPrenom().trim());
        patient.setCin(patient.getCin().trim().toUpperCase());
        patient.setTelephone(patient.getTelephone().trim());

        return new PermissionResponce<>(PermissionKey.MODIFIER_PATIENT, patient);
    }
}
