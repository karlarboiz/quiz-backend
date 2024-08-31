package com.example.demo.model.logic;

import com.example.demo.model.dao.entity.UserInformation;

import java.sql.SQLException;
import java.util.List;

public interface UserInformationLogic {
    public void saveUserInformation(UserInformation userInformation);

    public UserInformation matchedLoginCredentials(String email) ;

    public List<Object[]> matchedLoginCredentialsUsingUsername(String username) ;


    public List<UserInformation> getUserInfoByUsername(String username);
}
