package com.tropisure.adminserviceapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class CognitoService implements ICognitoClientService{

    private final CognitoIdentityProviderClient cognitoClient;

    @Override
    public void createUser(String userPoolId, String username, String email, String temporaryPassword, String group) {
        AdminCreateUserRequest request = AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("email_verified").value("true").build()
                )
                .temporaryPassword(temporaryPassword)
                .messageAction(MessageActionType.SUPPRESS)
                .build();
        cognitoClient.adminCreateUser(request);

        // add to group
        cognitoClient.adminAddUserToGroup(AdminAddUserToGroupRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .groupName(group)
                .build());
    }
}
