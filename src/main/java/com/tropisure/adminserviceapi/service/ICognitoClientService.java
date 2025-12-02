package com.tropisure.adminserviceapi.service;


public interface ICognitoClientService {

    void createUser(String userPoolId, String username, String email, String temporaryPassword, String group);

}
