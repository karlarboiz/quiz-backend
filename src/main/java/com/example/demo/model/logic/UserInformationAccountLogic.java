package com.example.demo.model.logic;

import com.example.demo.model.dao.entity.UserInformationAccount;

import java.sql.SQLException;

public interface UserInformationAccountLogic {
    public void saveUserInformationAccount(UserInformationAccount userInformationAccount);

    public UserInformationAccount getUserInfoByUserIdPk(int userIdPk) throws SQLException;
}
