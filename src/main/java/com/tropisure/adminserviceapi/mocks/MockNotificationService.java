package com.tropisure.adminserviceapi.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local") // Only used when running locally
public class MockNotificationService implements NotificationService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Mock email sent to: " + to + " Subject: " + subject);
    }
}

