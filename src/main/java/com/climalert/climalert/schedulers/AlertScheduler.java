package com.climalert.climalert.schedulers;

import com.climalert.climalert.service.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertScheduler {

    private final AlertService alertService;

    public AlertScheduler(AlertService alertService) {
        this.alertService = alertService;
    }

    @Scheduled(fixedRate = 60000) // 1 minuto
    public void run() {
        alertService.evaluateAndNotify();
    }
}