package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.dto.UserDTO;
import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.Repository.SecretaireRepository;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.behaviorPackBuilder.BehaviorPackBuilder;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.MedecinMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.RendezVousRepositoryImpl;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousAppService {

    private RendezVousRepositoryImpl rendezVousRepository;
    private CabinetRepository cabinetRepository;
    private RendezVousMapper rvm;
    private CabinetMapper cm;
    private PatientMapper pm;
    private SpringRendezVousRepository springRendezVousRepository;
    private SpringSecretaireRepository secretaireRepository;
    private MedecinRepository medecinRepository;
    private NotificationService notificationService ;

    public RendezVousAppService(
            RendezVousRepositoryImpl rendezVousRepository, CabinetRepository cabinetRepository, RendezVousMapper rvm, CabinetMapper cm, PatientMapper pmImp,
            RendezVousRepository rendezVousRepository,
            SpringSecretaireRepository secretaireRepository,
            MedecinRepository medecinRepository, 
            NotificationService notificationService ,
            SpringRendezVousRepository springRendezVousRepository) {
        this.rendezVousRepositoryImp = rendezVousRepositoryImp;
        this.secretaireRepository = secretaireRepository;
        this.medecinRepository = medecinRepository;
        this.springRendezVousRepository = springRendezVousRepository;
        this.cabinetRepository = cabinetRepository;
        this.rvm = rvm;
        this.cm = cm;
        this.pm = pm;
    }

    private Cabinet getCabinetFromUser(UserDTO user) {

        CabinetEntity cabinetEntity;

        if (AuthService.secretaireRole.equals(user.getRole())) {
            cabinetEntity = secretaireRepository.findCabinetBySecretaireId(user.getId());
        } else if (AuthService.medecinRole.equals(user.getRole())) {
            cabinetEntity = medecinRepository.findCabinetByMedecin(user.getId());
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

    // * Les rendez vous en attente */
    private List<RendezVous> getRendezVousFromCabinet(int idCabinet) {

        Date currentDate = new Date();
        List<RendezVousEntity> rendezVousEnityList = springRendezVousRepository
                .findByCabinet_IdCabinetAndDateRendezVousAfter(idCabinet, currentDate);
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

        Cabinet cabinet = getCabinetFromUser(user);
        List<RendezVous> rendezVousList = getRendezVousFromCabinet(cabinet.getIdCabinet());

        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());
        FeatureParameter<List<RendezVous>> parameter = new FeatureParameter<>(Featurekey.GET_SECRETAIRE, rendezVousList);
        FeatureResponce<List<RendezVous>> response = behaviorPack.performWork(parameter);

        List<RendezVousDTO> rendezVousDTOList = response.getPayload().stream()
                .map(RendezVousMapper::toDTO)
                .collect(Collectors.toList());

        return rendezVousDTOList;
    }





    public Boolean setRendezVousOnGoing(RendezVousDTO rendezVousDTO , UserDTO user){


        Cabinet cabinet = getCabinetFromUser(user);
        BehaviorPack behaviorPack = BehaviorPackBuilder.build(cabinet.getOffre());

        // On passe par behavior pack 
        RendezVous rendezVous = RendezVousMapper.toDomain(rendezVousDTO) ;
        FeatureParameter<RendezVous> parameter = new FeatureParameter<>(Featurekey.SET_RDV_ONGOING, rendezVous);
        FeatureResponce<RendezVous> response = behaviorPack.performWork(parameter);

        // On presiste les donnes 
        RendezVousEntity rendezVousEntity = RendezVousMapper.toEntity(response.getPayload()) ;
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

}
