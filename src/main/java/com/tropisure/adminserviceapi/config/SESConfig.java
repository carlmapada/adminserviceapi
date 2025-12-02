package com.tropisure.adminserviceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@Profile("prod")
public class SESConfig {

    @Bean
    public SesClient sesClient(AwsCredentialsProvider creds, @Value("${aws.region}") String region) {
        return SesClient.builder()
                .credentialsProvider(creds)
                .region(Region.of(region))
                .build();
    }
}

