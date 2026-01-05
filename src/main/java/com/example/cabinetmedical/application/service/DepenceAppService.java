package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.exception.DepenceNotFoundException;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.DepenceEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.DepenceMapper;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.Depence.DepenceRepositoryImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class DepenceAppService {
    private DepenceMapper dm;
    private DepenceRepositoryImpl depenceRepository;
    private CabinetRepository cabinetRepository;
    private CabinetMapper cm;

    public DepenceAppService(DepenceMapper dm, DepenceRepositoryImpl depenceRepository,  CabinetRepository cabinetRepository, CabinetMapper cm) {
        this.dm = dm;
        this.depenceRepository = depenceRepository;
        this.cabinetRepository = cabinetRepository;
        this.cm =cm;
    }

    public List<Depence> getAllbyCabinetAndMonth(int idCabinet, Date start, Date end){
        return dm.toDomainList(depenceRepository.findbyidCabinetAndDateBetween(idCabinet, start, end));
    }
    public List<Depence> getall(int idCabinet){
        return dm.toDomainList(depenceRepository.findallbyidCabinet(idCabinet));
    }

    public Depence save(Depence depence, int idCabinet) {

        CabinetEntity cabinet = cabinetRepository.findByIdCabinet(idCabinet);

        if (depence.getType() == DepenceType.PERIODIC) {

            DepenceEntity root = dm.toEntity(depence);
            root.setType(DepenceType.INITIAL_PERIODIC);
            root.setAnchorDate(depence.getDate());
            root.setCabinet(cabinet);

            DepenceEntity savedRoot = depenceRepository.save(root);

            DepenceEntity periodic = dm.toEntity(depence);
            periodic.setType(DepenceType.PERIODIC);
            periodic.setCabinet(cabinet);
            periodic.setParent(savedRoot);

            DepenceEntity savedPeriodic = depenceRepository.save(periodic);
            Depence savedPeriodicDomain = dm.toDomain(savedPeriodic);
            savedPeriodicDomain.setCabinet(cm.toDomain(cabinet));

            return savedPeriodicDomain;
        }

        DepenceEntity depenceEntity = dm.toEntity(depence);
        depenceEntity.setCabinet(cabinet);

        Depence savedDepence = dm.toDomain(depenceRepository.save(depenceEntity));
        savedDepence.setCabinet(cm.toDomain(cabinet));



        return savedDepence;
    }


    public void delete(Depence depence, int idCabinet) {
        DepenceEntity depenceEntity = depenceRepository.findById(depence.getIdDepence()).orElseThrow(() -> new DepenceNotFoundException("depence not found"));
        System.out.println("Depence to be deleted"+depenceEntity);
        CabinetEntity cabinet = cabinetRepository.findByIdCabinet(idCabinet);
        depenceEntity.setCabinet(cabinet);

        if (depenceEntity.getType() == DepenceType.INITIAL_PERIODIC) {
            List<DepenceEntity> children = depenceRepository.findAllByParent(depenceEntity);
            for (DepenceEntity child : children) {
                depenceRepository.delete(child);
            }
        }

        depenceRepository.delete(depenceEntity);
    }

    public Depence update(Depence depence, int idCabinet) {
        System.out.println("depence to be deleted: " + depence);

        DepenceEntity existing = depenceRepository.findById(depence.getIdDepence())
                .orElseThrow(() -> new DepenceNotFoundException("depence not found"));

        switch (existing.getType()) {

            case ONE_TIME, PERIODIC -> {
                existing.setDescription(depence.getDescription());
                existing.setPrix(depence.getPrix());
                existing.setDate(depence.getDate());
            }

            case INITIAL_PERIODIC -> {
                existing.setDescription(depence.getDescription());
                existing.setPrix(depence.getPrix());
                existing.setPeriodeJour(depence.getPeriodeJour());
                existing.setDate(depence.getDate());
                existing.setAnchorDate(depence.getDate());
                existing.setLastGenratedDate(new Date());
            }
        }
        Depence processedDepence = dm.toDomain(existing);
        return save(processedDepence, idCabinet);
    }


    public void processRecurringDepences() {
        Date now = new Date();

        List<DepenceEntity> roots = depenceRepository.findByType(DepenceType.INITIAL_PERIODIC); //we find the initial periodic type transactions (they're the roots for the remaining periodic type transactions and are the ones that are initially given by the user)

        for (DepenceEntity root : roots) {

            int cabinetId = root.getCabinet().getIdCabinet();
            CabinetEntity cabinet = cabinetRepository.findByIdCabinet(cabinetId);

            Date anchor = root.getAnchorDate(); //the root is our reference for periods, it's immutable and would be = date for initial_periodic types. it serves to give us a clear indicator on what transactions we missed in case the system is down for a set time > period
            int period = root.getPeriodeJour(); //the period of the transaction

            Date last = root.getLastGenratedDate(); //the last time we renewed the expense
            if (last == null) {
                last = anchor; //if we dont have it then we return to the anchor
            }

            long daysSinceAnchor =
                    ChronoUnit.DAYS.getDuration().toMillis() == 0
                            ? 0
                            : (last.getTime() - anchor.getTime()) / (24L * 60 * 60 * 1000); // this serves to how many days have passed between the anchor and the last time we generated an expense

            long nextIndex = (daysSinceAnchor / period) + 1; //we index the time at which we last renewed the expense

            while (true) { //this whole sequence is meant to add one periodic type transactions. (multiple if the system's been down for a while and we missed a couple of transactions)
                Date scheduledDate = new Date( // (this gives us the date at which the expense should be renewed
                        anchor.getTime()
                                + nextIndex * period * 24L * 60 * 60 * 1000
                );

                if (scheduledDate.after(now)) break; //if the date is in the future, that means we dont have to do anything just yet and we leave

                Depence generated = new Depence(); //if not we create a new expense
                generated.setDescription(root.getDescription());
                generated.setPrix(root.getPrix());
                generated.setType(DepenceType.PERIODIC); //with periodic type
                generated.setPeriodeJour(period);
                generated.setDate(scheduledDate);
                generated.setParent(dm.toDomain(root));


                DepenceEntity generatedEntity = dm.toEntity(generated);
                generatedEntity.setCabinet(cabinet);
                generatedEntity.setParent(root);



                depenceRepository.save(generatedEntity); //and then we save it

                root.setLastGenratedDate(scheduledDate);
                nextIndex++; //then we move on to the next index, to check if we missed something or not
            }
            depenceRepository.save(root); //then we update the root with the latest last generated date
        }
    }

}
