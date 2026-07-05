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
        System.out.println("MAILTRAP_USER visto por la JVM: " + System.getenv("MAILTRAP_USER"));
        System.out.println("MAILTRAP_PASSWORD length: " +
                (System.getenv("MAILTRAP_PASSWORD") != null ? System.getenv("MAILTRAP_PASSWORD").length() : "NULL"));

        Weather weatherCritico = new Weather("Buenos Aires", 38.5, 75, "Sunny", LocalDateTime.now());
        emailService.sendAlertEmail(weatherCritico);
        Thread.sleep(3000);
    }
}