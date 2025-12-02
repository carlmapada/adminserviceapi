package com.tropisure.adminserviceapi.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class MockCognitoClientService implements ICognitoClientService {

    @Override
    public void createUser(String userPoolId, String username, String email, String temporaryPassword, String group) {
        System.out.println("Mock Cognito create user: " + username + " with role " + group);
    }
}
