package com.tropisure.adminserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class SESNotificationService implements NotificationService{

    private final SesClient sesClient;

    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(to).build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).build())
                        .body(Body.builder().text(Content.builder().data(body).build()).build())
                        .build())
                .source("no-reply@tropisure.com")
                .build();
        sesClient.sendEmail(request);
    }
}

