package com.example.cabinetmedical.application.service;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.application.dto.FactureDTO;
import com.example.cabinetmedical.application.dto.RequestConsultationDTO;
import com.example.cabinetmedical.application.dto.UserDTO;
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

        RendezVousEntity rendezVousEntity = springRendezVousRepository.findByIdRendezVous(idRendezVous);
        if (rendezVousEntity == null) {
            throw new EntityNotFoundException("RendezVous with id " + idRendezVous + " not found");
       }

       RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousEntity) ;
       Consultation consultation = ConsultationMapper.toDomain(requestConsultationDTO) ;


        Cabinet cabinet = getCabinetByEmail(user);

        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());
        CreerConsultationPayload payload = new CreerConsultationPayload() ;
        payload.setConsultation(consultation);
        payload.setRendezVous(rendezVous);
        payload.setCabinet(cabinet);
        FeatureParameter<CreerConsultationPayload> parameter = new FeatureParameter<>(Featurekey.CREE_CONSULTATION,payload) ;
        FeatureResponce<Consultation> response = behaviorPack.performWork(parameter);

        ConsultationMapper consultationMapper = new ConsultationMapper() ;

        ConsultationEntity consultationEntity = consultationMapper.toEntity(response.getPayload()) ;
        consultationEntity = consultationRepository.save(consultationEntity) ;
        
        FactureEntity factureEntity = consultationEntity.getFacture() ;
        FactureMapper   factureMapper = new FactureMapper() ;
        FactureDTO factureDTO = factureMapper.toDto(factureEntity) ;

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
}
