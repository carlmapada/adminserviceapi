package com.tropisure.adminserviceapi.mocks;

import com.tropisure.adminserviceapi.service.ICognitoClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class MockCognitoClientService implements ICognitoClientService {

    @Override
    public String createUser(String userPoolId, String username, String email, String temporaryPassword, String group) {
        System.out.println("Mock Cognito create user: " + username + " with role " + group);
        return "xxxxx-sub";
    }
}
