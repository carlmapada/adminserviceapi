package com.tropisure.adminserviceapi.service;


public interface ICognitoClientService {

    String createUser(String userPoolId, String username, String email, String temporaryPassword, String group);

}
