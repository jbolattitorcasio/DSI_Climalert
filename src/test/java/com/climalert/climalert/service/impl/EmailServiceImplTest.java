package com.climalert.climalert.service.impl;

import com.climalert.climalert.model.Weather;
import com.climalert.climalert.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    void deberiaEnviarUnMailDeAlerta() throws InterruptedException {
        Weather weatherCritico = new Weather("Buenos Aires", 38.5, 75, "Sunny", LocalDateTime.now());
        emailService.sendAlertEmail(weatherCritico);
        Thread.sleep(3000);
    }
}