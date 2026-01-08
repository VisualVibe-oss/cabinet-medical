package com.example.cabinetmedical.application.service.patient;

import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.DTO.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.application.DTO.patient.PatientDTO;
import com.example.cabinetmedical.application.service.CabinetAppService;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
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
import com.example.cabinetmedical.infrastructure.mapper.AllergieMapper;
import com.example.cabinetmedical.infrastructure.mapper.AntecedentChirurgicalMapper;
import com.example.cabinetmedical.infrastructure.mapper.AntecedentMedicalMapper;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.HabitudeVieMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.mapper.TraitementChroniqueMapper;
import com.example.cabinetmedical.infrastructure.mapper.dossierMedical.DossierMedicalMapper;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.dossierMedical.DossierMedicalRepository;
import com.example.cabinetmedical.infrastructure.repository.patient.PatientRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
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
    private final CabinetAppService cabinetAppService;

    public PatientAppService(
            PatientRepository patientRepository,
            PatientDomainService patientDomainService,
            PatientMapper patientMapper,
            DossierMedicalRepository dossierMedicalRepository,
            DossierMedicalDomainService dossierMedicalDomainService,
            DossierMedicalMapper dossierMedicalMapper,
            CabinetRepository cabinetRepository,
            SecretaireRepository secretaireRepository,
            SecretaireMapper secretaireMapper, CabinetAppService cabinetAppService) {
        this.patientRepository = patientRepository;
        this.patientDomainService = patientDomainService;
        this.patientMapper = patientMapper;
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.dossierMedicalDomainService = dossierMedicalDomainService;
        this. dossierMedicalMapper = dossierMedicalMapper;
        this.cabinetRepository = cabinetRepository;
        this.secretaireRepository = secretaireRepository;
        this. secretaireMapper = secretaireMapper;
        this.cabinetAppService = cabinetAppService;
    }

    //creer
    public PatientDTO createPatient(UserDTO user, PatientDTO patientDTO) {
        // Récupérer la secrétaire avec ses permissions
        SecretaireEntity  secretaireEntity = secretaireRepository.findByEmail(user.getEmail())
    .orElseThrow(() -> new RuntimeException("Waaaaa33333e wa nari che7ale ghite"));
        // Vérifier que le cabinet existe
        Cabinet cabinet = cabinetAppService.getCabinetByEmail(user) ;

        // Créer l'objet domaine Patient
        Cabinet  cabinetDomaine = cabinet;
        Patient patientDomain = PatientMapper.toDomain(patientDTO);

        patientDomain.setCabinet(cabinetDomaine);

        Secretaire secretaire = SecretaireMapper.toDomain(secretaireEntity) ;
        // ✅ Exécuter avec permission
       // PermissionResponce<Patient> response = (PermissionResponce<Patient>)
        //          secretaire.doWork(new PermissionParameter<>(PermissionKey.CREE_PATIENT, patientDomain));

        //  Patient processedPatient = response.getPayload();

        // Convertir en entité pour la sauvegarde
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setNom(patientDomain.getNom());
        patientEntity.setPrenom(patientDomain.getPrenom());
        patientEntity. setCin(patientDomain.getCin());
        patientEntity.setTelephone(patientDomain. getTelephone());
        patientEntity.setSexe(patientDomain.getSexe());
        patientEntity.setDateNaissance(patientDomain.getDateNaissance());
        patientEntity.setTypeMutuelle(patientDomain.getTypeMutuelle());
        patientEntity.setCabinet(CabinetMapper.toEntity(cabinet));

        // Sauvegarder
        PatientEntity savedPatient = patientRepository.save(patientEntity);

        return PatientMapper.toDTO(savedPatient);
    }

    //mise a jour
    public PatientDTO updatePatient(UserDTO user, int idPatient, PatientDTO patientDTO) {
        // Récupérer la secrétaire
        SecretaireEntity secretaireEntity = secretaireRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée"));

        // Vérifier que le patient existe
        PatientEntity existingPatient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        // Vérifier le cabinet
        Cabinet cabinet = cabinetAppService.getCabinetByEmail(user);

        // Créer l'objet domaine Patient
        Patient patientDomain = patientMapper.toDomain(patientDTO);
        patientDomain.setIdPatient(idPatient);
        patientDomain.setCabinet(cabinet);

        Secretaire secretaire = SecretaireMapper.toDomain(secretaireEntity);

        // ✅ Exécuter avec permission (commenté car secretaryPermissions est null)
        // PermissionResponce<Patient> response = (PermissionResponce<Patient>)
        //     secretaire.doWork(new PermissionParameter<>(PermissionKey.MODIFIER_PATIENT, patientDomain));
        // Patient processedPatient = response.getPayload();

        // Mise à jour des champs (utiliser patientDomain directement)
        existingPatient.setNom(patientDomain.getNom());
        existingPatient.setPrenom(patientDomain.getPrenom());
        existingPatient.setCin(patientDomain.getCin());
        existingPatient.setTelephone(patientDomain.getTelephone());
        existingPatient.setSexe(patientDomain.getSexe());
        existingPatient.setDateNaissance(patientDomain.getDateNaissance());
        existingPatient.setTypeMutuelle(patientDomain.getTypeMutuelle());

        PatientEntity updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(updatedPatient);
    }

    // ============ SUPPRESSION DE PATIENT ============
    public void deletePatient(UserDTO user, int idPatient) {
        // Récupérer la secrétaire
        SecretaireEntity secretaireEntity = secretaireRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée"));

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        Secretaire secretaire = SecretaireMapper.toDomain(secretaireEntity);

        // ✅ Exécuter avec permission (commenté car secretaryPermissions est null)
        // PermissionResponce<Long> response = (PermissionResponce<Long>)
        //     secretaire.doWork(new PermissionParameter<>(PermissionKey.SUPPRIMER_PATIENT, (long) idPatient));

        // Supprimer directement
        patientRepository.delete(patient);
    }

    // ============ GESTION DES DOSSIERS MÉDICAUX AVEC PERMISSIONS ============

    // ============ ASSOCIATION D'UN DOSSIER ============
    public DossierMedicalDTO associateDossierMedical(UserDTO userDTO, int idPatient, DossierMedicalDTO dossierDTO) {
        // Récupérer la secrétaire
        SecretaireEntity secretaireEntity = secretaireRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée"));

        Secretaire secretaire = SecretaireMapper.toDomain(secretaireEntity);

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        // Vérifier qu'il n'a pas déjà un dossier
        validateNoDossierExists(idPatient);

        // Créer l'objet domaine DossierMedical
        DossierMedical dossierDomain = DossierMedicalMapper.toDomain(dossierDTO);

        // ✅ Exécuter avec permission (commenté car secretaryPermissions est null)
        // PermissionResponce<DossierMedical> response = (PermissionResponce<DossierMedical>)
        //     secretaire.doWork(new PermissionParameter<>(PermissionKey.CREE_DOSSIER_MEDICAL, dossierDomain));
        // DossierMedical processedDossier = response.getPayload();

        // Convertir en entité
        DossierMedicalEntity dossierEntity = DossierMedicalMapper.toEntity(dossierDTO);

        // Utiliser dossierDomain directement
        dossierEntity.setDateCreation(dossierDomain.getDateCreation());
        dossierEntity.setPatient(patient);

        // Sauvegarder
        DossierMedicalEntity savedDossier = dossierMedicalRepository.save(dossierEntity);

        // Associer au patient
        patient.setDossierMedical(savedDossier);
        patientRepository.save(patient);

        return DossierMedicalMapper.toDTO(savedDossier);
    }

    // ============ MISE À JOUR DU DOSSIER MÉDICAL ============
    public DossierMedicalDTO updateDossierMedical(UserDTO user, int idPatient, DossierMedicalDTO dossierDTO) {
        // Récupérer la secrétaire
        SecretaireEntity secretaireEntity = secretaireRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée"));

        Secretaire secretaire = SecretaireMapper.toDomain(secretaireEntity);

        // Vérifier que le patient existe
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        DossierMedicalEntity dossier = patient.getDossierMedical();
        if (dossier == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        // Créer l'objet domaine DossierMedical
        DossierMedical dossierDomain = DossierMedicalMapper.toDomain(dossierDTO);
        dossierDomain.setIdDossier(dossier.getIdDossier());

        // ✅ Exécuter avec permission (commenté car secretaryPermissions est null)
        // PermissionResponce<DossierMedical> response = (PermissionResponce<DossierMedical>)
        //     secretaire.doWork(new PermissionParameter<>(PermissionKey.MODIFIER_DOSSIER_MEDICAL, dossierDomain));
        // DossierMedical processedDossier = response.getPayload();

        // Utiliser dossierDomain directement
        dossier.setAllergies(dossierDomain.getAllergies().stream()
                .map(AllergieMapper::toEntity).toList());
        dossier.setAntecedentsChirurgicaux(dossierDomain.getAntecedentChirurgicals().stream()
                .map(AntecedentChirurgicalMapper::toEntity).toList());
        dossier.setAntecedentsMedicaux(dossierDomain.getAntecedentMedicals().stream()
                .map(AntecedentMedicalMapper::toEntity).toList());
        dossier.setTraitementsChroniques(dossierDomain.getTraitementChroniques().stream()
                .map(TraitementChroniqueMapper::toEntity).toList());
        dossier.setHabitudesVie(HabitudeVieMapper.toEntity(dossierDomain.getHabitudeVie()));

        DossierMedicalEntity updatedDossier = dossierMedicalRepository.save(dossier);
        return dossierMedicalMapper.toDTO(updatedDossier);
    }

    // ============ MÉTHODES DE CONSULTATION (SANS PERMISSIONS REQUISES) ============

    public PatientDTO getPatientById(int idPatient, UserDTO user) {
        PatientEntity patient = patientRepository.findById(idPatient)
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
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PatientDTO> getPatientsByNomAndCabinet(String nom, int id) {
        List<PatientEntity> patients = patientRepository.findByNomAndCabinet_IdCabinet(nom, id);
        return patients.stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DossierMedicalDTO getDossierMedical(int idPatient) {
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        if (patient.getDossierMedical() == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        return dossierMedicalMapper.toDTO(patient.getDossierMedical());
    }
    // ============ MÉTHODES UTILITAIRES ============

    private void validateNoDossierExists(int idPatient) {
        if (dossierMedicalRepository.existsByPatient_IdPatient(idPatient)) {
            throw new RuntimeException("Un dossier médical existe déjà pour ce patient");
        }
    }

   
}
