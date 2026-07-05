package com.climalert.climalert.service;

import com.climalert.climalert.model.Weather;

public interface EmailService {
    void sendAlertEmail(Weather weather);
}