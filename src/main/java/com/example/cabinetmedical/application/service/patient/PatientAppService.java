package com.example.cabinetmedical.application.service.patient;

import com.example.cabinetmedical.application.dto.dossierMedical.DossierMedicalDTO;
import com.example.cabinetmedical.application.dto.patient.PatientDTO;
import com.example.cabinetmedical.domain.service.dossierMedical.DossierMedicalDomainService;
import com.example.cabinetmedical.domain.service.patient.PatientDomainService;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.mapper.dossierMedical.DossierMedicalMapper;
import com.example.cabinetmedical.infrastructure.mapper.patient.PatientMapper;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.dossierMedical.DossierMedicalRepository;
import com.example.cabinetmedical.infrastructure.repository.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientAppService {
    private final PatientRepository patientRepository;
    private final PatientDomainService patientDomainService;
    private final PatientMapper patientMapper;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalDomainService dossierMedicalDomainService;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final CabinetRepository cabinetRepository;


    public PatientAppService(PatientRepository patientRepository, PatientDomainService patientDomainService, PatientMapper patientMapper, DossierMedicalRepository dossierMedicalRepository, DossierMedicalDomainService dossierMedicalDomainService, DossierMedicalMapper dossierMedicalMapper, CabinetRepository cabinetRepository) {
        this.patientRepository = patientRepository;
        this.patientDomainService = patientDomainService;
        this.patientMapper = patientMapper;
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.dossierMedicalDomainService = dossierMedicalDomainService;
        this.dossierMedicalMapper = dossierMedicalMapper;
        this.cabinetRepository = cabinetRepository;
    }

    private void validateNoDossierExists(int idPatient) {
        if (dossierMedicalRepository.existsByPatient_IdPatient(idPatient)) {
            throw new RuntimeException("Un dossier médical existe déjà pour ce patient");
        }
    }
    //creer patient
    public PatientDTO createPatient(PatientDTO patientDTO) {


        // Vérifier que le cabinet existe
        CabinetEntity cabinet = cabinetRepository.findById(patientDTO.getIdCabinet())
                .orElseThrow(() -> new RuntimeException("Cabinet non trouvé avec l'ID :  " + patientDTO.getIdCabinet()));

        // Créer l'entité patient
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setNom(patientDTO.getNom());
        patientEntity.setPrenom(patientDTO.getPrenom());
        patientEntity.setCin(patientDTO.getCin());
        patientEntity.setTelephone(patientDTO.getTelephone());
        patientEntity. setSexe(patientDTO.getSexe());
        patientEntity.setDateNaissance(patientDTO.getDateNaissance());
        patientEntity.setTypeMutuelle(patientDTO.getTypeMutuelle());
        patientEntity.setCabinet(cabinet);

        // Gérer le dossier médical si présent
        if (patientDTO.getDossierMedical() != null) {
            DossierMedicalEntity dossierEntity = dossierMedicalMapper.toEntity(patientDTO. getDossierMedical());
            dossierEntity.setDateCreation(new Date());
            DossierMedicalEntity savedDossier = dossierMedicalRepository.save(dossierEntity);
            patientEntity. setDossierMedical(savedDossier);
        }

        // Sauvegarder le patient
        PatientEntity savedPatient = patientRepository.save(patientEntity);

        // Retourner le DTO
        return patientMapper.toDTO(savedPatient);
    }

    public PatientDTO getPatientById(int idPatient) {
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        return patientMapper.toDTO(patient);
    }

    public PatientDTO getPatientByCinAndCabinet(String cin,int id) {
        PatientEntity patient=patientRepository.findByCinAndCabinet_IdCabinet(cin,id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec cin : " + cin));
        return patientMapper.toDTO(patient);
    }

    /**
     * Récupérer tous les patients d'un cabinet
     */
    public List<PatientDTO> getAllPatientsByCabinet(int idCabinet) {
        List<PatientEntity> patients = patientRepository.findByIdCabinet(idCabinet);
        return patients.stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les patients par nom
     */
    public List<PatientDTO> getPatientsByNomAndCabinet(String nom,int id) {
        List<PatientEntity> patients = patientRepository.findByNomAndCabinet_IdCabinet(nom,id);
        return patients.stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mettre à jour un patient
     */
    public PatientDTO updatePatient(int idPatient, PatientDTO patientDTO) {
        PatientEntity existingPatient = patientRepository. findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        // Validation du CIN
        if (! existingPatient.getCin().equals(patientDTO.getCin())) {
            existingPatient.setCin(patientDTO.getCin());
        }

        // Mise à jour des champs
        existingPatient.setNom(patientDTO.getNom());
        existingPatient.setPrenom(patientDTO.getPrenom());
        existingPatient.setTelephone(patientDTO.getTelephone());
        existingPatient.setSexe(patientDTO.getSexe());
        existingPatient.setDateNaissance(patientDTO.getDateNaissance());
        existingPatient.setTypeMutuelle(patientDTO.getTypeMutuelle());

        PatientEntity updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(updatedPatient);
    }

    /**
     * Supprimer un patient
     */
    public void deletePatient(int idPatient) {
        PatientEntity patient = patientRepository. findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        patientRepository.delete(patient);
    }

    /**
     * Associer un dossier médical à un patient
     */
    public DossierMedicalDTO associateDossierMedical(int idPatient, DossierMedicalDTO dossierDTO) {
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        validateNoDossierExists(idPatient);

        // Créer le dossier
        DossierMedicalEntity dossierEntity = dossierMedicalMapper.toEntity(dossierDTO);
        dossierEntity.setDateCreation(new Date());
        dossierEntity.setPatient(patient);

        DossierMedicalEntity savedDossier = dossierMedicalRepository.save(dossierEntity);

        // Associer au patient
        patient.setDossierMedical(savedDossier);
        patientRepository. save(patient);

        return dossierMedicalMapper.toDTO(savedDossier);
    }

    /**
     * Mettre à jour le dossier médical
     */
    public DossierMedicalDTO updateDossierMedical(int idPatient, DossierMedicalDTO dossierDTO) {
        PatientEntity patient = patientRepository. findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        DossierMedicalEntity dossier = patient.getDossierMedical();
        if (dossier == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        // Mise à jour
        dossier.setAntMedicaux(dossierDTO.getAntMedicaux());
        dossier.setAntChirug(dossierDTO.getAntChirug());
        dossier.setAllergies(dossierDTO.getAllergies());
        dossier.setTraitement(dossierDTO.getTraitement());
        dossier.setHabitudes(dossierDTO.getHabitudes());
        dossier.setHistoriqueConsultations(dossierDTO.getHistoriqueConsultations());
        dossier.setDocumentsMedicaux(dossierDTO.getDocumentsMedicaux());

        DossierMedicalEntity updatedDossier = dossierMedicalRepository. save(dossier);
        return dossierMedicalMapper.toDTO(updatedDossier);
    }

    /**
     * Récupérer le dossier médical d'un patient
     */
    public DossierMedicalDTO getDossierMedical(int idPatient) {
        PatientEntity patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID : " + idPatient));

        if (patient.getDossierMedical() == null) {
            throw new RuntimeException("Aucun dossier médical trouvé pour ce patient");
        }

        return dossierMedicalMapper.toDTO(patient. getDossierMedical());
    }



}
