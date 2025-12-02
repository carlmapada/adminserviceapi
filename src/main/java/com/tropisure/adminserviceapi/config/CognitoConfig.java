package com.tropisure.adminserviceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
@Profile("prod")
public class CognitoConfig {

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return EnvironmentVariableCredentialsProvider.create();
    }

    @Bean
    public CognitoIdentityProviderClient cognitoClient(AwsCredentialsProvider creds,
                                                       @Value("${aws.region}") String region) {
        return CognitoIdentityProviderClient.builder()
                .credentialsProvider(creds)
                .region(Region.of(region))
                .build();
    }
}

