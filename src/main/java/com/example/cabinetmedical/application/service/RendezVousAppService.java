package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.PatientDTO;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.behaviorPackBuilder.BehaviorPackBuilder;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.RendezVousRepositoryImpl;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousAppService {

    private RendezVousRepositoryImpl rendezVousRepositoryImpl;
    private RendezVousRepository rendezVousRepository;
    private CabinetRepository cabinetRepository;
    private RendezVousMapper rvm;
    private CabinetMapper cm;
    private PatientMapper pm;
    private SpringRendezVousRepository springRendezVousRepository;
    private SpringSecretaireRepository secretaireRepository;
    private MedecinRepository medecinRepository;
    private NotificationService notificationService ;
    private CabinetAppService  cabinetService ; 


    public RendezVousAppService(
            RendezVousRepositoryImpl rendezVousRepositoryImpl, CabinetRepository cabinetRepository, RendezVousMapper rvm, CabinetMapper cm, PatientMapper pmImp,
            RendezVousRepository rendezVousRepository,
            SpringSecretaireRepository secretaireRepository,
            MedecinRepository medecinRepository, 
            CabinetAppService  cabinetService  ,
            NotificationService notificationService ,
            SpringRendezVousRepository springRendezVousRepository) {

        this.rendezVousRepositoryImpl = rendezVousRepositoryImpl;
        this.rendezVousRepository = rendezVousRepository;
        this.secretaireRepository = secretaireRepository;
        this.medecinRepository = medecinRepository;
        this.cabinetService  =cabinetService ;
        this.springRendezVousRepository = springRendezVousRepository;
        this.cabinetRepository = cabinetRepository;
        this.rvm = rvm;
        this.cm = cm;
        this.pm = pm;
    }


    // * Les rendez vous en attente */
    private List<RendezVous> getRendezVousFromCabinet(int idCabinet) {

        LocalDateTime currentDate = LocalDateTime.now();
        List<RendezVousEntity> rendezVousEnityList = springRendezVousRepository
                .findByCabinet_IdCabinetAndDateDebutRendezVous(idCabinet, currentDate);
        List<RendezVous> rendezVousList = rendezVousEnityList.stream()
                .map(rendezVousEntity->{
                     RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousEntity) ;
                     rendezVous.setPatient(PatientMapper.toDomain(rendezVousEntity.getPatient()));
                     return rendezVous ; 
                })
                .collect(Collectors.toList());
        return rendezVousList;

    }

    

    public RendezVousDTO create(RendezVous rv, int idCabinet)  {

        RendezVousEntity rve = rvm.toEntity(rv);
        rve.setCabinet(cabinetRepository.findByIdCabinet(idCabinet));
        rve.setPatient(pm.toEntity(rv.getPatient()));
        rendezVousRepository.save(rve);
        RendezVousDTO rvdto = rvm.toDTO(rve);
        rvdto.setIdCabinet(idCabinet);
        rvdto.setPatient(rv.getPatient());


        return rvdto;
    }

    public List<RendezVousDTO> getAllRendezVous(int idCabinet){
        List<RendezVousEntity> rves = rendezVousRepository.findAllByCabinet_idCabinet(idCabinet);
        List<RendezVousDTO> rvs = rvm.toDTOList(rves);
        for (int i = 0; i < rvs.size(); i++) {
            RendezVousEntity entity = rves.get(i);
            RendezVousDTO dto = rvs.get(i);

            if (entity.getPatient() != null) {
                dto.setPatient(pm.toDomain(entity.getPatient()));
            }
        }
        System.out.println("getAllRendezVous "+rvs);
        return rvs;
    }

    public RendezVous getRendezVous(int idRendezVous){
        return rvm.toDomain(rendezVousRepository.findById(idRendezVous));
    }


    

    public List<RendezVousDTO> getRendezVous(UserDTO user) {

        Cabinet cabinet = cabinetService.getCabinetFromUser(user);
        List<RendezVous> rendezVousList = getRendezVousFromCabinet(cabinet.getIdCabinet());

        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());
        FeatureParameter<List<RendezVous>> parameter = new FeatureParameter<>(Featurekey.GET_RDV_LIST, rendezVousList);
        FeatureResponce<List<RendezVous>> response = behaviorPack.performWork(parameter);

        List<RendezVousDTO> rendezVousDTOList = response.getPayload().stream()
                .map(RendezVousMapper::toDTO)
                .collect(Collectors.toList());

        return rendezVousDTOList;
    }





    public Boolean setRendezVousOnGoing(RendezVousDTO rendezVousDTO , UserDTO user){


        Cabinet cabinet = cabinetService.getCabinetFromUser(user);
        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());

        // On passe par behavior pack 
        RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousDTO) ;
        FeatureParameter<RendezVous> parameter = new FeatureParameter<>(Featurekey.SET_RDV_ONGOING, rendezVous);
        FeatureResponce<RendezVous> response = behaviorPack.performWork(parameter);

        // On presiste les donnes 
        RendezVousEntity rendezVousEntity = RendezVousMapper.toEntity(response.getPayload()) ;
        Patient patient = rendezVous.getPatient() ;
        if(patient != null){
            rendezVousEntity.setPatient(PatientMapper.toEntity(patient)) ;
        }   
        

        int ligneAffecte = springRendezVousRepository.setSateRendezVous(rendezVousEntity) ;
        if(ligneAffecte == 0 ){
            throw new EntityNotFoundException("Ce rendez vous n'existe pas"+ rendezVousDTO.getIdRendezVous() ) ;
        }

        // diffuse un message au medecin qu il a une consultation en cours 
        String emailMedcin = cabinet.getMedecin().getEmail() ;
        if(emailMedcin == null){
            throw new EntityNotFoundException("Ce medecin avec cet email n'existe pas :"+ emailMedcin) ;
        }
        
        notificationService.consultationReadyNotification(emailMedcin ,"Tu as une consiltation");
        
        return  true ; 
    }






    
    public RendezVousDTO getRendezVousByIdPatient(int idPatient , UserDTO user) {

        Cabinet cabinet =cabinetService.getCabinetByEmail(user);
        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());

        RendezVousEntity rendezVousEntity = springRendezVousRepository.findByPatient_IdPatient(idPatient) ;

        
        if(rendezVousEntity == null){
            throw new DataIntegrityViolationException("Aucun rendez vous n'existe pour ce patient avec id :"+ idPatient) ;
        }

        PatientEntity patientEntity = rendezVousEntity.getPatient() ; 
        
        // On passe par behavior pack 
        RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousEntity) ;
        FeatureParameter<RendezVous> parameter = new FeatureParameter<>(Featurekey.GET_RDV_INFO, rendezVous);
        FeatureResponce<RendezVous> response = behaviorPack.performWork(parameter);

        RendezVousDTO rendezVousDTO =  RendezVousMapper.toDTO(rendezVous)  ;
        Patient patient =  PatientMapper.toDomain(patientEntity) ;

        rendezVousDTO.setPatient(patient);

        
        return rendezVousDTO;
    }



        public RendezVousDTO getRendezVousById(int idRendezVous , UserDTO user) {

        Cabinet cabinet = cabinetService.getCabinetByEmail(user);
        
        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());

        RendezVousEntity rendezVousEntity = springRendezVousRepository.findByIdRendezVous(idRendezVous) ;

        
        if(rendezVousEntity == null){
            throw new DataIntegrityViolationException("Aucun rendez vous existe  avec cet id :"+ idRendezVous) ;
        }

        PatientEntity patientEntity = rendezVousEntity.getPatient() ; 
        
        // On passe par behavior pack 
        RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousEntity) ;
        //! Le key du fonctionnalite est le meme que getRendezVousByIdPatient , a changer si besoin
        FeatureParameter<RendezVous> parameter = new FeatureParameter<>(Featurekey.GET_RDV_INFO, rendezVous);
        FeatureResponce<RendezVous> response = behaviorPack.performWork(parameter);

        RendezVousDTO rendezVousDTO =  RendezVousMapper.toDTO(rendezVous)  ;
        Patient patient =  PatientMapper.toDomain(patientEntity) ;

        rendezVousDTO.setPatient(patient);

        
        return rendezVousDTO;
    }
}
