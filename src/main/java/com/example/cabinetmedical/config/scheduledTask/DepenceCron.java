package com.example.cabinetmedical.config.scheduledTask;

import com.example.cabinetmedical.application.service.DepenceAppService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DepenceCron {
    private final DepenceAppService depenceAppService;

    public DepenceCron(DepenceAppService depenceAppService) {
        this.depenceAppService = depenceAppService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void RecurringExpenseScheduler(){
        depenceAppService.processRecurringDepences();
    }
}
