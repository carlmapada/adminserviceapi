package com.tropisure.adminserviceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@Profile("prod")
public class SESConfig {

    @Value("${aws.cognito.access-key}")
    private String accessKey;

    @Value("${aws.cognito.secret-key}")
    private String secretKey;

    @Value("${aws.cognito.region}")
    private String region;

    @Bean
    public SesClient sesClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        return SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();
    }
}

