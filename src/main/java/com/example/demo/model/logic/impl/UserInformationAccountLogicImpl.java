package com.example.demo.model.logic.impl;

import com.example.demo.model.dao.UserInformationAccountDao;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.dao.entity.UserInformationAccount;
import com.example.demo.model.logic.UserInformationAccountLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserInformationAccountLogicImpl implements UserInformationAccountLogic {

    @Autowired
    private UserInformationAccountDao userInformationAccountDao;

    @Override
    public void saveUserInformationAccount(UserInformationAccount userInformationAccount) {
        userInformationAccountDao.save(userInformationAccount);
    }

    @Override
    public UserInformationAccount getUserInfoByUserIdPk(int userIdPk) throws SQLException{
        return userInformationAccountDao.getUserByUserIdPk(userIdPk);
    }
}
