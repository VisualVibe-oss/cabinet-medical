package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.DepenceDTO;
import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.SecretaireDTO;
import com.example.cabinetmedical.application.DTO.Stats.ChartDTO;
import com.example.cabinetmedical.application.DTO.Stats.StatsDTO;
import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.domain.model.Medecin.*;
import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.Stats.StatsFunctionality;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.*;
import com.example.cabinetmedical.domain.utils.payload.*;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.mapper.*;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SecretaireRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
public class MedecinAppService {


    private final RendezVousAppService rendezVousAppService;
    private SecretaireMapper sm;
    private MedecinMapper mm;
    private MedecinRepository medecinRepositoryImpl;
    private SecretaireAppService secretaireAppService;
    private FactureAppService factureAppService;
    private DepenceAppService depenceAppService;
    private DepenceMapper dm;
    private CabinetMapper cm;
    private CabinetRepository cabinetRepository;
    private RendezVousMapper rvm;
    private CabinetAppService  cabinetAppService;


    public MedecinAppService(MedecinMapper mm, SecretaireMapper sm, MedecinRepository medecinRepositoryImpl, SecretaireAppService secretaireAppService, RendezVousAppService rendezVousAppService, FactureAppService factureAppService, DepenceAppService depenceAppService, DepenceMapper dm, CabinetMapper cm, CabinetRepository cabinetRepository, RendezVousMapper rvm, CabinetAppService cabinetAppService) {
        this.mm = mm;
        this.sm = sm;
        this.medecinRepositoryImpl = medecinRepositoryImpl;
        this.secretaireAppService = secretaireAppService;
        this.rendezVousAppService = rendezVousAppService;
        this.factureAppService = factureAppService;
        this.depenceAppService = depenceAppService;
        this.dm = dm;
        this.cm = cm;
        this.cabinetRepository = cabinetRepository;
        this.rvm = rvm;
        this.cabinetAppService = cabinetAppService;
    }

    public Object addSecretary(SecretaireDTO secretaireDTO) {

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();
        //TEST ONLY
        int idCabinet = secretaireDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));
        BehaviorPack behaviorPack = new BehaviorPack();
        cabinet.setBehaviorPack(behaviorPack);

        behaviorPack.addFeature(Featurekey.ADD_SECRETAIRE, new AddSecretaire());

        Offre offre = new Offre();
        offre.setType(OfferType.BASIC);
        cabinet.setOffre(offre);
        //

        Secretaire secretaire = sm.toDomain(secretaireDTO);
        List<Secretaire> total = sm.toDomainListFromDto(secretaireAppService.getAllSecretaries(idCabinet));

        String upperEmail = secretaire.getEmail().toUpperCase();
        secretaire.setEmail(upperEmail);

        List<String> allSecretaryEmails = Optional.ofNullable(secretaireAppService.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Secretaire::getEmail)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> allDoctorEmails = Optional.ofNullable(this.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Medecin::getEmail)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        AddSecretairePayload addSecretairePayload = new AddSecretairePayload();

        secretaire.setCabinet(cabinet);

        System.out.println(total.size());
        addSecretairePayload.setSecretaire(secretaire);
        addSecretairePayload.setTotal(total);
        addSecretairePayload.setExistingMedecinEmails(allDoctorEmails);
        addSecretairePayload.setExistingSecretaireEmails(allSecretaryEmails);

        if (cabinet.getOffre().getType()== OfferType.BASIC){
            addSecretairePayload.setMaxEmployees(2);
        }
        else if(cabinet.getOffre().getType()==OfferType.PRO){
            addSecretairePayload.setMaxEmployees(5);
        }

        FeatureResponce<?> responce = cabinet.getBehaviorPack().performWork(new FeatureParameter<>(Featurekey.ADD_SECRETAIRE, addSecretairePayload));

        if (responce.getPayload() instanceof Secretaire processedSecretaire) {
            return sm.toDTO(secretaireAppService.saveSecretaire(processedSecretaire, idCabinet));
        } else {
            return responce.getPayload().toString();
        }

    }

    public List<SecretaireDTO> getAllSecretaries(int idCabinet) {
        //for the future
       // int actualidCabinet = cabinetAppService.getCurrentCabinet().getIdCabinet();
        //
        return secretaireAppService.getAllSecretaries(idCabinet);
    }

    public SecretaireDTO updateSecretaire(SecretaireDTO secretaireDTO) {

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();



        //TEST ONLY
        int idCabinet = secretaireDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));
        BehaviorPack behaviorPack = new BehaviorPack();
        cabinet.setBehaviorPack(behaviorPack);
        behaviorPack.addFeature(Featurekey.EDIT_SECRETAIRE, new EditSecretaire());

        //
        Secretaire current = secretaireAppService.findByidSecretaire(secretaireDTO.getIdSecretaire());
        current.setCabinet(cabinet);

        EditSecretairePayload payload = new EditSecretairePayload(
                current,
                secretaireDTO.getNom(),
                secretaireDTO.getPrenom(),
                secretaireDTO.getEmail(),
                secretaireDTO.getTelephone(),
                secretaireDTO.getSalaire(),
                secretaireDTO.getPermissionKeys()
        );

        FeatureResponce<Secretaire> responce = cabinet.getBehaviorPack().performWork(new FeatureParameter(Featurekey.EDIT_SECRETAIRE, payload));

        Secretaire processedSecretaire = responce.getPayload();

        return sm.toDTO(secretaireAppService.saveSecretaire(processedSecretaire, idCabinet));
    }


    public void deleteSecretaire(int idSecretaire){

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        Secretaire secretaire = secretaireAppService.findByidSecretaire(idSecretaire);
        Cabinet cabinet = secretaire.getCabinet();
        int idCabinet = cabinet.getIdCabinet();
        secretaire.setCabinet(cabinet);

        //TEST ONLY
        BehaviorPack behaviorPack = new BehaviorPack();
        behaviorPack.addFeature(Featurekey.DELETE_SECRETAIRE, new DeleteSecretaire());
        cabinet.setBehaviorPack(behaviorPack);
        //

        FeatureResponce response = cabinet.getBehaviorPack()
                .performWork(new FeatureParameter<>(Featurekey.DELETE_SECRETAIRE, secretaire));

        Secretaire processedSecretaire = (Secretaire) response.getPayload();

        secretaireAppService.delete(processedSecretaire, idCabinet);
    }

    public List<Medecin> findAll(){
        List<MedecinEntity> mes= medecinRepositoryImpl.findAll();
        return mm.toDomainList(mes);
    }
    public List<RendezVousDTO> getAllRendezVous(int idCabinet){

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        return rendezVousAppService.getAllRendezVous(idCabinet);
    }
    public RendezVousDTO editRendezVous(RendezVousDTO rendezVousDTO) {

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        int idCabinet = rendezVousDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));
        RendezVous current = rendezVousAppService.getRendezVous(rendezVousDTO.getIdRendezVous());
        current.setCabinet(cabinet);
        List<RendezVous> existingrvs = rvm.toDomainList(rendezVousAppService.getAllRendezVous(idCabinet));


        //TEMPORARY
        BehaviorPack behaviorPack = new BehaviorPack();
        behaviorPack.addFeature(Featurekey.EDIT_RENDEZ_VOUS, new EditRendezVous());
        cabinet.setBehaviorPack(behaviorPack);
        //


        EditRendezVousPayload payload = new EditRendezVousPayload(
                current,
                rendezVousDTO.getDateDebutRendezVous(),
                rendezVousDTO.getDateFinRendezVous(),
                rendezVousDTO.getMotif(),
                rendezVousDTO.getStatut(),
                rendezVousDTO.getNotes(),
                cabinet,
                rendezVousDTO.getPatient(),
                existingrvs
        );

        FeatureResponce<RendezVous> responce = cabinet.getBehaviorPack().performWork(new FeatureParameter(Featurekey.EDIT_RENDEZ_VOUS, payload));
        RendezVous processedRendezVous = responce.getPayload();

        return rendezVousAppService.create(processedRendezVous, idCabinet);
    }

    public StatsDTO getStats(int idCabinet){

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        //TEST ONLY
        Cabinet cabinet = new Cabinet();
        BehaviorPack behaviorPack = new BehaviorPack();
        cabinet.setBehaviorPack(behaviorPack);

        behaviorPack.addFeature(Featurekey.VIEW_STATS, new StatsFunctionality());
        //

        YearMonth currentMonth = YearMonth.now();

        LocalDate monthStartLD = currentMonth.atDay(1);
        LocalDate monthEndLD = currentMonth.atEndOfMonth();

        Date monthStart = Date.from(monthStartLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date monthEnd = Date.from(monthEndLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Depence> monthDepences = depenceAppService.getAllbyCabinetAndMonth(idCabinet, monthStart, monthEnd);
        List<Facture> monthFacture = factureAppService.findAllbyCabinetAndMonth(idCabinet, monthStart, monthEnd);

        YearMonth lastMonth = currentMonth.minusMonths(1);

        LocalDate lastMonthStartLD = lastMonth.atDay(1);
        LocalDate lastMonthEndLD = lastMonth.atEndOfMonth();

        Date lastMonthStart = Date.from(lastMonthStartLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date lastMonthEnd = Date.from(lastMonthEndLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Depence> lastMonthDepences=depenceAppService.getAllbyCabinetAndMonth(idCabinet, lastMonthStart, lastMonthEnd);
        List<Facture> lastMonthFactures=factureAppService.findAllbyCabinetAndMonth(idCabinet, lastMonthStart, lastMonthEnd);

        //chart stats

        List<Depence> allDepences =depenceAppService.getall(idCabinet);
        List<Facture> allFactures =factureAppService.findAll(idCabinet);

        StatsPayload payload = new StatsPayload(lastMonthDepences, lastMonthFactures, allDepences, allFactures, monthDepences, monthFacture);

        FeatureResponce<StatsDTO> responce = cabinet.performWork(new FeatureParameter(Featurekey.VIEW_STATS, payload));

        return  responce.getPayload();
    }

    public DepenceDTO addDepence(DepenceDTO depenceDTO){
        Depence depence = dm.toDomain(depenceDTO);

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();


        int idCabinet = depenceDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));



        //TEST ONLY
        BehaviorPack behaviorPack = new BehaviorPack();
        behaviorPack.addFeature(Featurekey.ADD_DEPENCE, new AddDepence());
        cabinet.setBehaviorPack(behaviorPack);
        //

        depence.setCabinet(cabinet);

        FeatureResponce responce = cabinet.getBehaviorPack().performWork(new FeatureParameter(Featurekey.ADD_DEPENCE, depence));

        Depence processedDepence =  (Depence) responce.getPayload();
        return dm.toDTO(depenceAppService.save(processedDepence, idCabinet));
    }

    public void deleteDepence(DepenceDTO depenceDTO){

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        Depence depence = dm.toDomain(depenceDTO);
        int idCabinet = depenceDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));
        //TEST ONLY
        BehaviorPack behaviorPack = new BehaviorPack();
        behaviorPack.addFeature(Featurekey.DELETE_DEPENCE, new DeleteDepence());
        cabinet.setBehaviorPack(behaviorPack);
        //
        depence.setCabinet(cabinet);

        FeatureResponce responce = cabinet.getBehaviorPack().performWork(new FeatureParameter(Featurekey.DELETE_DEPENCE, depence));

       Depence processedDepennce =  (Depence) responce.getPayload();

       depenceAppService.delete(processedDepennce, idCabinet);
    }

    public DepenceDTO updateDepence(DepenceDTO depenceDTO){

        //Cabinet actualCabinet = cabinetAppService.getCurrentCabinet();
        //int actuatidCabinet = actualCabinet.getIdCabinet();

        Depence depence = dm.toDomain(depenceDTO);
        int idCabinet = depenceDTO.getIdCabinet();
        Cabinet cabinet = cm.toDomain(cabinetRepository.findByIdCabinet(idCabinet));

        //TEST ONLY
        BehaviorPack behaviorPack = new BehaviorPack();
        behaviorPack.addFeature(Featurekey.EDIT_DEPENCE, new EditDepence());
        cabinet.setBehaviorPack(behaviorPack);
        //

        depence.setCabinet(cabinet);


        FeatureResponce responce = cabinet.getBehaviorPack().performWork(new FeatureParameter(Featurekey.EDIT_DEPENCE, depence));
        Depence processedDepennce =  (Depence) responce.getPayload();
        return dm.toDTO(depenceAppService.update(processedDepennce, idCabinet));
    }



}
