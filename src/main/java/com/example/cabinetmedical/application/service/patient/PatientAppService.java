package com.example.cabinetmedical.application.service.patient;

import com.example.cabinetmedical.application.dto.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.application.dto.patient.PatientDTO;
import com.example.cabinetmedical.domain.model.Employee.SecretaryPermissions;
import com.example.cabinetmedical.domain.model.dossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.service.dossierMedical.DossierMedicalDomainService;
import com.example.cabinetmedical.domain.service.patient.PatientDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.dossierMedical.DossierMedicalMapper;
import com.example.cabinetmedical.infrastructure.mapper.patient.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.secretaire.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.dossierMedical.DossierMedicalRepository;
import com.example.cabinetmedical.infrastructure.repository.patient.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientAppService {
    private final PatientRepository patientRepository;
    private final PatientDomainService patientDomainService;
    private final PatientMapper patientMapper;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalDomainService dossierMedicalDomainService;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final CabinetRepository cabinetRepository;
    private final SecretaireRepository secretaireRepository;
    private final SecretaireMapper secretaireMapper;

    public PatientAppService(
            PatientRepository patientRepository,
            PatientDomainService patientDomainService,
            PatientMapper patientMapper,
            DossierMedicalRepository dossierMedicalRepository,
            DossierMedicalDomainService dossierMedicalDomainService,
            DossierMedicalMapper dossierMedicalMapper,
            CabinetRepository cabinetRepository,
            SecretaireRepository secretaireRepository,
            SecretaireMapper secretaireMapper) {
        this.patientRepository = patientRepository;
        this.patientDomainService = patientDomainService;
        this.patientMapper = patientMapper;
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.dossierMedicalDomainService = dossierMedicalDomainService;
        this. dossierMedicalMapper = dossierMedicalMapper;
        this.cabinetRepository = cabinetRepository;
        this.secretaireRepository = secretaireRepository;
        this. secretaireMapper = secretaireMapper;
    }

    //creer
    public PatientDTO createPatient(int idSecretaire, PatientDTO patientDTO) {
        // Récupérer la secrétaire avec ses permissions
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le cabinet existe
        CabinetEntity cabinet = cabinetRepository.findById(patientDTO.getIdCabinet())
                .orElseThrow(() -> new RuntimeException("Cabinet non trouvé avec l'ID : " + patientDTO.getIdCabinet()));

        // Créer l'objet domaine Patient
        Patient patientDomain = patientMapper. toDomain(patientDTO);

        // ✅ Exécuter avec permission
        PermissionResponce<Patient> response = (PermissionResponce<Patient>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.CREE_PATIENT, patientDomain));

        Patient processedPatient = response.getPayload();

        // Convertir en entité pour la sauvegarde
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setNom(processedPatient.getNom());
        patientEntity.setPrenom(processedPatient.getPrenom());
        patientEntity. setCin(processedPatient.getCin());
        patientEntity.setTelephone(processedPatient. getTelephone());
        patientEntity.setSexe(processedPatient.getSexe());
        patientEntity.setDateNaissance(processedPatient.getDateNaissance());
        patientEntity.setTypeMutuelle(processedPatient.getTypeMutuelle());
        patientEntity.setCabinet(cabinet);

        // Sauvegarder
        PatientEntity savedPatient = patientRepository.save(patientEntity);

        return patientMapper.toDTO(savedPatient);
    }

    //mise a jour
    public PatientDTO updatePatient(int idSecretaire, int idPatient, PatientDTO patientDTO) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le patient existe
        PatientEntity existingPatient = patientRepository. findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        // Créer l'objet domaine Patient
        Patient patientDomain = patientMapper.toDomain(patientDTO);
        patientDomain.setIdPatient(idPatient);

        // ✅ Exécuter avec permission
        PermissionResponce<Patient> response = (PermissionResponce<Patient>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.MODIFIER_PATIENT, patientDomain));

        Patient processedPatient = response. getPayload();

        // Mise à jour des champs
        existingPatient. setNom(processedPatient.getNom());
        existingPatient.setPrenom(processedPatient.getPrenom());
        existingPatient.setCin(processedPatient.getCin());
        existingPatient.setTelephone(processedPatient.getTelephone());
        existingPatient.setSexe(processedPatient.getSexe());
        existingPatient.setDateNaissance(processedPatient.getDateNaissance());
        existingPatient. setTypeMutuelle(processedPatient.getTypeMutuelle());

        PatientEntity updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(updatedPatient);
    }

    //supprimer
    public void deletePatient(int idSecretaire, int idPatient) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        // ✅ Exécuter avec permission
        PermissionResponce<Long> response = (PermissionResponce<Long>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.SUPPRIMER_PATIENT, (long) idPatient));

        // Supprimer
        patientRepository. delete(patient);
    }

    // ============ GESTION DES DOSSIERS MÉDICAUX AVEC PERMISSIONS ============

    //assosier un dossier
    public DossierMedicalDTO associateDossierMedical(int idSecretaire, int idPatient, DossierMedicalDTO dossierDTO) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID :  " + idPatient));

        // Vérifier qu'il n'a pas déjà un dossier
        validateNoDossierExists(idPatient);

        // Créer l'objet domaine DossierMedical
        DossierMedical dossierDomain = dossierMedicalMapper.toDomain(dossierDTO);

        // ✅ Exécuter avec permission
        PermissionResponce<DossierMedical> response = (PermissionResponce<DossierMedical>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.CREE_DOSSIER_MEDICAL, dossierDomain));

        DossierMedical processedDossier = response.getPayload();

        // Convertir en entité
        DossierMedicalEntity dossierEntity = dossierMedicalMapper.toEntity(dossierDTO);
        dossierEntity. setDateCreation(processedDossier.getDateCreation());
        dossierEntity.setPatient(patient);

        // Sauvegarder
        DossierMedicalEntity savedDossier = dossierMedicalRepository.save(dossierEntity);

        // Associer au patient
        patient.setDossierMedical(savedDossier);
        patientRepository.save(patient);

        return dossierMedicalMapper.toDTO(savedDossier);
    }

    /**
     * Mettre à jour le dossier médical avec vérification des permissions
     */
    public DossierMedicalDTO updateDossierMedical(int idSecretaire, int idPatient, DossierMedicalDTO dossierDTO) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        DossierMedicalEntity dossier = patient.getDossierMedical();
        if (dossier == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        // Créer l'objet domaine DossierMedical
        DossierMedical dossierDomain = dossierMedicalMapper.toDomain(dossierDTO);
        dossierDomain.setIdDossier(dossier.getIdDossier());

        // ✅ Exécuter avec permission
        PermissionResponce<DossierMedical> response = (PermissionResponce<DossierMedical>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.MODIFIER_DOSSIER_MEDICAL, dossierDomain));

        DossierMedical processedDossier = response.getPayload();

        // Mise à jour
        dossier.setAntMedicaux(processedDossier.getAntMedicaux());
        dossier.setAnprocessedDossiertChirug(processedDossier.getAntChirug());
        dossier.setAllergies(processedDossier.getAllergies());
        dossier.setTraitement(processedDossier.getTraitement());
        dossier.setHabitudes(processedDossier. getHabitudes());
        dossier.setHistoriqueConsultations(processedDossier.getHistoriqueConsultations());
        dossier.setDocumentsMedicaux(processedDossier.getDocumentsMedicaux());

        DossierMedicalEntity updatedDossier = dossierMedicalRepository.save(dossier);
        return dossierMedicalMapper. toDTO(updatedDossier);
    }

    // ============ MÉTHODES DE CONSULTATION (SANS PERMISSIONS REQUISES) ============

    public PatientDTO getPatientById(int idPatient) {
        PatientEntity patient = patientRepository. findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));
        return patientMapper.toDTO(patient);
    }

    public PatientDTO getPatientByCinAndCabinet(String cin, int id) {
        PatientEntity patient = patientRepository.findByCinAndCabinet_IdCabinet(cin, id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec cin : " + cin));
        return patientMapper.toDTO(patient);
    }

    public List<PatientDTO> getAllPatientsByCabinet(int idCabinet) {
        List<PatientEntity> patients = patientRepository.findByIdCabinet(idCabinet);
        return patients.stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PatientDTO> getPatientsByNomAndCabinet(String nom, int id) {
        List<PatientEntity> patients = patientRepository. findByNomAndCabinet_IdCabinet(nom, id);
        return patients.stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DossierMedicalDTO getDossierMedical(int idPatient) {
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        if (patient.getDossierMedical() == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        return dossierMedicalMapper.toDTO(patient. getDossierMedical());
    }

    // ============ MÉTHODES UTILITAIRES ============

    private void validateNoDossierExists(int idPatient) {
        if (dossierMedicalRepository.existsByPatient_IdPatient(idPatient)) {
            throw new RuntimeException("Un dossier médical existe déjà pour ce patient");
        }
    }

    //recuperer sec
    private Secretaire findSecretaireById(int idSecretaire) {
        var secretaireEntity = secretaireRepository.findById(idSecretaire)
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée avec l'ID : " + idSecretaire));

        // ✅ Le mapper injecte automatiquement SecretaryPermissions
        return secretaireMapper.toDomain(secretaireEntity);
    }
}
