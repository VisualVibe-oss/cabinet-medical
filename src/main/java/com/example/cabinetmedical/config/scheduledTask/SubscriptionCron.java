package com.example.cabinetmedical.config.scheduledTask;

import org.springframework.data.domain.Page;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;



@Component
public class SubscriptionCron {


    private final CabinetRepository cabinetRepository;
    public SubscriptionCron(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }

    private Date addDays(Date date, int days) {
        if (date == null) return null;
        Instant instant = date.toInstant().plus(days, ChronoUnit.DAYS);
        return (Date) Date.from(instant);
    }  

    private Page<CabinetEntity> getCabinetsWithExpiredSubscriptions(Pageable pageable) {
        Date now = new Date(System.currentTimeMillis()) ;
        return cabinetRepository.findByDateFinOffreBefore( now,pageable);
    }


     


    private void handleExpiredSubscriptions(List<CabinetEntity> cabinets) {
        for (CabinetEntity cabinet : cabinets) {
            Date newDateFinOffre = addDays(cabinet.getDateFinOffre() , cabinet.getOffre().getOffreDurationInDays()) ;
            cabinetRepository.updateDateFinOffreByIdCabinet(cabinet.getIdCabinet() , newDateFinOffre) ;
        }
    }   

    @Scheduled(cron = "0 0 10 * * ?")
    public void verifySubscriptionCron() {
        try {
            int pageSize = 500 ; 
            int pageNumbre = 0 ; 
             Page <CabinetEntity> result ;
            do {
                Pageable page = PageRequest.of(pageNumbre, pageSize) ;
                result = getCabinetsWithExpiredSubscriptions(page) ;
                handleExpiredSubscriptions(result.getContent());
                pageNumbre++ ; 
            } while (result.hasNext());
        } catch (Exception e) {
            System.err.println("Erreur dans le cron du subscription");
        }
    }
}
