package com.example.demo.model.logic.impl;



import com.example.demo.model.dao.UserInformationDao;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import org.apache.catalina.User;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserInformationLogicImpl implements UserInformationLogic {

    @Autowired
    private UserInformationDao userInformationDao;

    @Override
    public void saveUserInformation(UserInformation userInformation) {


        userInformationDao.saveAndFlush(userInformation);
    }

    @Override
    public UserInformation matchedLoginCredentials(String email) {
        return userInformationDao.matchedLoginCredentials(email);
    }

    @Override
    public List<Object[]> matchedLoginCredentialsUsingUsername(String username)  {

        return userInformationDao.matchedLoginCredentialsUsingUsername(username);
    }


    @Override
    public List<UserInformation> getUserInfoByUsername(String username) {
        return userInformationDao.getUserInfoByUsername(username);
    }


 }
