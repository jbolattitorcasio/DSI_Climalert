package com.climalert.climalert.service.impl;

import com.climalert.climalert.model.Weather;
import com.climalert.climalert.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private static final String[] RECIPIENTS = {
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    };

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendAlertEmail(Weather weather) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(RECIPIENTS);
        message.setSubject("Alerta climática - " + weather.getLocation());
        message.setText(buildBody(weather));

        mailSender.send(message);
        log.info("Mail de alerta enviado para: {}", weather);
    }

    private String buildBody(Weather weather) {
        return """
				Se ha detectado una condición climática peligrosa.

				Ubicación: %s
				Temperatura: %.1f °C
				Humedad: %d%%
				Condición: %s
				Fecha y hora: %s
				""".formatted(
                weather.getLocation(),
                weather.getTemperatureC(),
                weather.getHumidity(),
                weather.getCondition(),
                weather.getFetchedAt()
        );
    }
}