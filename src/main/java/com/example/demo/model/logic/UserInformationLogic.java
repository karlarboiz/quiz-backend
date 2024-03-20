package com.example.demo.model.logic;

import com.example.demo.model.dao.entity.UserInformation;

import java.sql.SQLException;

public interface UserInformationLogic {
    public void saveUserInformation(UserInformation userInformation);

    public UserInformation matchedLoginCredentials(String email) ;

    public UserInformation matchedLoginCredentialsUsingUsername(String username) ;
}
