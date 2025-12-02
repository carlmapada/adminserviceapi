package com.tropisure.adminserviceapi.service;

public interface NotificationService {
    void sendEmail(String to, String subject, String body);
}