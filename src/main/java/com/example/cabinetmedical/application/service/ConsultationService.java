package com.example.cabinetmedical.application.service;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.application.DTO.ConsultationResponseDTO;
import com.example.cabinetmedical.application.DTO.FactureDTO;
import com.example.cabinetmedical.application.DTO.RequestConsultationDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.behaviorPackBuilder.BehaviorPackBuilder;
import com.example.cabinetmedical.domain.model.consultation.Consultation;
import com.example.cabinetmedical.domain.model.functionnalities.CreerConsultation;
import com.example.cabinetmedical.domain.model.functionnalities.payload.CreerConsultationPayload;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.ExamenEntity;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.ConsultationMapper;
import com.example.cabinetmedical.infrastructure.mapper.ConsultationResponseMapper;
import com.example.cabinetmedical.infrastructure.mapper.FactureMapper;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.ConsultationRepository;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultationService {
    

   private final SpringRendezVousRepository springRendezVousRepository;
    private final ConsultationRepository consultationRepository;
    private final SpringSecretaireRepository secretaireRepository;
    private final MedecinRepository medecinRepository;
    private final NotificationService notificationService;
    
   

    // CONSTRUCTEUR POUR L'INJECTION (Remplace @Autowired sur chaque champ)
    public ConsultationService(
            SpringRendezVousRepository springRendezVousRepository,
            ConsultationRepository consultationRepository,
            SpringSecretaireRepository secretaireRepository,
            MedecinRepository medecinRepository,
            NotificationService notificationService) {
        this.springRendezVousRepository = springRendezVousRepository;
        this.consultationRepository = consultationRepository;
        this.secretaireRepository = secretaireRepository;
        this.medecinRepository = medecinRepository;
        this.notificationService = notificationService;
        
    }





     private Cabinet getCabinetByEmail(UserDTO user) {

        CabinetEntity cabinetEntity;

        if (AuthService.secretaireRole.equals(user.getRole())) {
            cabinetEntity = secretaireRepository.findCabinetBySecretaireEmail(user.getEmail());
        } else if (AuthService.medecinRole.equals(user.getRole())) {
            cabinetEntity = medecinRepository.findCabinetByMedecinEmail(user.getEmail());
        } else {
            throw new IllegalArgumentException("Rôle utilisateur invalide : " + user.getRole());
        }

        if (cabinetEntity == null) {
            throw new IllegalArgumentException("Utilisateur invalide : ");
        }

        Cabinet cabinet = CabinetMapper.toDomain(cabinetEntity);
        Medecin medecin = MedecinMapper.toDomain(cabinetEntity.getMedecin()) ;
        cabinet.setMedecin(medecin);

        

        return cabinet;
    }

    public boolean createConsultation(int idRendezVous, UserDTO user,  RequestConsultationDTO requestConsultationDTO) {

        // recuperation du rendezVousEntity  ( rendezVous ne contient pas le cle secondaire du consultation c est consulatoin qui le possed)
        RendezVousEntity rendezVousEntity = springRendezVousRepository.findByIdRendezVous(idRendezVous);
        if (rendezVousEntity == null) {
            throw new EntityNotFoundException("RendezVous with id " + idRendezVous + " not found");
       }
        System.out.println("C'est le rendezVousEntity Recuperer" + rendezVousEntity.getIdRendezVous());

        //  je mappe juste les champs primitve
       RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousEntity) ;
       Consultation consultation = ConsultationMapper.toDomain(requestConsultationDTO) ;


        // aucun traitmenent ici avec la base du donnes ;
        Cabinet cabinet = getCabinetByEmail(user);

        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());
        CreerConsultationPayload payload = new CreerConsultationPayload() ;
        payload.setConsultation(consultation);
        payload.setRendezVous(rendezVous);
        payload.setCabinet(cabinet);
        FeatureParameter<CreerConsultationPayload> parameter = new FeatureParameter<>(Featurekey.CREE_CONSULTATION,payload) ;
        //  On lie la consulation avec  le cabinet domaine  cabinetENtity Toujour deja exsite 
        FeatureResponce<Consultation> response = behaviorPack.performWork(parameter);


        // On mapper la consultation 
        //  le mappage conssiste  a  une mappger des Entite aussis en relation cascade avec   consultationEntity 
        //  On mappe aussis la facture et on le met en consultationEntity 

        ConsultationMapper consultationMapper = new ConsultationMapper() ;

        ConsultationEntity consultationEntity = consultationMapper.toEntity(response.getPayload()) ;

        // On met le rendezVous avec la consultation Entiy ( toujour le rendezVous deja exsite et je pense qu il est pas changer pour une autre object )
        consultationEntity.setRendezVous(rendezVousEntity);
        consultationEntity = consultationRepository.save(consultationEntity) ;
        
        
        FactureEntity factureEntity = consultationEntity.getFacture() ;
        FactureDTO factureDTO = FactureMapper.toDto(factureEntity) ;

        // On recupere les email des secretaire du cabinet
        int idCabinet = cabinet.getIdCabinet() ;
        List<String> emailsSecretaire = secretaireRepository.findEmailsByCabinetId(idCabinet) ;
        
        if(emailsSecretaire.isEmpty()) {
            throw new EntityNotFoundException("Aucune secrétaire trouvée pour le cabinet avec l'id " + idCabinet);
        }

        notificationService.facturePendingNotification(
            emailsSecretaire, 
            factureDTO
        ) ;


        return true ; 
    
    }






    public List<ConsultationResponseDTO> getPreviousConsultationsByRendezVousId(int idRendezVous) {

        // 1️⃣ Charger le rendez-vous
        RendezVousEntity rendezVous = springRendezVousRepository.findById(idRendezVous)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

        // 2️⃣ Récupérer le patient
        int patientId = rendezVous.getPatient().getIdPatient();

        // 3️⃣ Charger toutes les consultations du patient
        List<ConsultationEntity> consultations =
                consultationRepository.findAllByPatientId(patientId);

        return consultations.stream()
                .map((consultationEntity) -> ConsultationResponseMapper.toDTO(consultationEntity))
                .toList();
    }
}
