package com.example.cabinetmedical.application.service.rendezVous;


import com.example.cabinetmedical.application.dto.rendezVous.RendezVousDTO;
import com.example.cabinetmedical.domain.model.rendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.service.rendezVous.RendezVousDomainService;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.rendezVous.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.mapper.secretaire.SecretaireMapper;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.patient.PatientRepository;
import com.example.cabinetmedical.infrastructure.repository.rendezVous.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RendezVousAppService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousDomainService rendezVousDomainService;
    private final RendezVousMapper rendezVousMapper;
    private final SecretaireRepository secretaireRepository;
    private final SecretaireMapper secretaireMapper;
    private final CabinetRepository cabinetRepository;
    private final PatientRepository patientRepository;

    public RendezVousAppService(
            RendezVousRepository rendezVousRepository,
            RendezVousDomainService rendezVousDomainService,
            RendezVousMapper rendezVousMapper,
            SecretaireRepository secretaireRepository,
            SecretaireMapper secretaireMapper,
            CabinetRepository cabinetRepository,
            PatientRepository patientRepository
            ) {
        this.rendezVousRepository = rendezVousRepository;
        this.rendezVousDomainService = rendezVousDomainService;
        this.rendezVousMapper = rendezVousMapper;
        this.secretaireRepository = secretaireRepository;
        this. secretaireMapper = secretaireMapper;
        this.cabinetRepository = cabinetRepository;
        this.patientRepository=patientRepository ;
    }

    //creer
    public RendezVousDTO createRendezVous(int idSecretaire, RendezVousDTO rendezVousDTO) {
        // Récupérer la secrétaire avec ses permissions
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Créer l'objet domaine RendezVous
        RendezVous rendezVousDomain = rendezVousMapper.toDomain(rendezVousDTO);

        // ✅ Exécuter avec permission
        PermissionResponce<RendezVous> response = (PermissionResponce<RendezVous>)
                secretaire. doWork(new PermissionParameter<>(PermissionKey.CREE_RENDEZ_VOUS, rendezVousDomain));

        RendezVous processedRendezVous = response. getPayload();

        // ✅ Charger les relations depuis la DB
        CabinetEntity cabinet = cabinetRepository.findById(rendezVousDTO.getIdCabinet())
                .orElseThrow(() -> new RuntimeException("Cabinet non trouvé"));

        PatientEntity patient = patientRepository.findById(rendezVousDTO.getIdPatient())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        // Convertir en entité et associer les relations
        RendezVousEntity rendezVousEntity = rendezVousMapper.toEntity(processedRendezVous);
        rendezVousEntity.setCabinet(cabinet);
        rendezVousEntity.setPatient(patient);

        // Sauvegarder
        RendezVousEntity savedRendezVous = rendezVousRepository.save(rendezVousEntity);

        return rendezVousMapper.toDTO(savedRendezVous);
    }

    //update
    public RendezVousDTO updateRendezVous(int idSecretaire, int idRendezVous, RendezVousDTO rendezVousDTO) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le rendez-vous existe
        RendezVousEntity existingRendezVous = rendezVousRepository.findById(idRendezVous)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'ID : " + idRendezVous));

        // Créer l'objet domaine
        RendezVous rendezVousDomain = rendezVousMapper.toDomain(rendezVousDTO);
        rendezVousDomain. setIdRendezVous(idRendezVous);

        // ✅ Exécuter avec permission
        PermissionResponce<RendezVous> response = (PermissionResponce<RendezVous>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.MODIFIER_RENDEZ_VOUS, rendezVousDomain));

        RendezVous processedRendezVous = response.getPayload();

        // ✅ Mettre à jour les champs qui existent dans l'entité
        existingRendezVous.setDateRendezVous(processedRendezVous.getDateRendezVous());
        existingRendezVous.setMotif(processedRendezVous.getMotif());
        existingRendezVous.setStatut(processedRendezVous.getStatut());
        existingRendezVous.setNotes(processedRendezVous.getNotes());

        RendezVousEntity updatedRendezVous = rendezVousRepository.save(existingRendezVous);
        return rendezVousMapper.toDTO(updatedRendezVous);
    }

    //delete
    public void deleteRendezVous(int idSecretaire, int idRendezVous) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // Vérifier que le rendez-vous existe
        RendezVousEntity rendezVous = rendezVousRepository.findById(idRendezVous)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'ID : " + idRendezVous));

        // ✅ Exécuter avec permission
        PermissionResponce<Long> response = (PermissionResponce<Long>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.SUPPRIMER_RENDEZ_VOUS, (long) idRendezVous));

        // Supprimer
        rendezVousRepository.delete(rendezVous);
    }

    //consulter
    public RendezVousDTO getRendezVousById(int idSecretaire, int idRendezVous) {
        // Récupérer la secrétaire
        Secretaire secretaire = findSecretaireById(idSecretaire);

        // ✅ Vérifier la permission de consultation
        PermissionResponce<Long> response = (PermissionResponce<Long>)
                secretaire.doWork(new PermissionParameter<>(PermissionKey.CONSULTER_RENDEZ_VOUS, (long) idRendezVous));

        // Récupérer le rendez-vous
        RendezVousEntity rendezVous = rendezVousRepository.findById(idRendezVous)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'ID : " + idRendezVous));

        return rendezVousMapper.toDTO(rendezVous);
    }

    // sans permissions
    public List<RendezVousDTO> getAllRendezVousByCabinet(int idCabinet) {
        List<RendezVousEntity> rendezVousList = rendezVousRepository.findByCabinet_IdCabinet(idCabinet);
        return rendezVousList.stream()
                .map(rendezVousMapper::toDTO)
                .collect(Collectors.toList());
    }

    //rv d un cab
    public List<RendezVousDTO> getRendezVousByPatient(int idPatient) {
        List<RendezVousEntity> rendezVousList = rendezVousRepository.findByPatient_IdPatient(idPatient);
        return rendezVousList.stream()
                .map(rendezVousMapper:: toDTO)
                .collect(Collectors.toList());
    }

    //par statut
    public List<RendezVousDTO> getRendezVousByStatut(String statut, int idCabinet) {
        List<RendezVousEntity> rendezVousList = rendezVousRepository.findByStatutAndCabinet_IdCabinet(statut, idCabinet);
        return rendezVousList.stream()
                .map(rendezVousMapper:: toDTO)
                .collect(Collectors.toList());
    }

    // ============ MÉTHODE UTILITAIRE ============

    private Secretaire findSecretaireById(int idSecretaire) {
        var secretaireEntity = secretaireRepository. findById(idSecretaire)
                .orElseThrow(() -> new RuntimeException("Secrétaire non trouvée avec l'ID : " + idSecretaire));

        return secretaireMapper.toDomain(secretaireEntity);
    }
}