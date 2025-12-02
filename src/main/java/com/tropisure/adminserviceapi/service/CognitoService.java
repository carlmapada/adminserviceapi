package com.tropisure.adminserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CognitoAdminService {

    private final CognitoIdentityProviderClient cognitoClient;

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    public void createUserWithRole(String email, String role) {
        AdminCreateUserRequest request = AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .temporaryPassword(UUID.randomUUID().toString())
                .userAttributes(AttributeType.builder().name("email").value(email).build())
                .messageAction(MessageActionType.SUPPRESS)
                .build();

        cognitoClient.adminCreateUser(request);

        // Add user to group/role
        cognitoClient.adminAddUserToGroup(AdminAddUserToGroupRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .groupName(role)
                .build());
    }
}

