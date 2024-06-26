package com.example.demo.model.service.impl;


import com.example.demo.model.dao.entity.Role;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.dao.entity.UserInformationAccount;
import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.logic.SaveGameInfoLogic;
import com.example.demo.model.logic.UserInformationAccountLogic;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.obj.UserInformationObj;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserInformationLogic userInformationLogic;

    @Autowired
    private UserInformationAccountLogic userInformationAccountLogic;

    @Autowired
    private SaveGameInfoLogic saveGameInfoLogic;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUserInformation(RegistrationInOutDto userInformationEntity) {
        UserInformation userInformation = new UserInformation();
        UserInformationAccount userInformationAccount = new UserInformationAccount();
        Timestamp date = new Timestamp(System.currentTimeMillis());


        userInformation.setEmail(userInformationEntity.getEmail());

        userInformation.setRegDate(date);
        userInformation.setUsername(userInformationEntity.getUsername());
        userInformation.setRole(Role.USER);
        userInformation.setPassword(passwordEncoder.encode(userInformationEntity.getPassword()));
        userInformation.setUpdateId("sample");
        userInformation.setRegId("Sample");
        userInformation.setUpdateDate(date);
        userInformation.setDeleteFlg(false);
        userInformationLogic.saveUserInformation(userInformation);


        userInformationAccount.setUserIdPk(userInformation.getIdPk());
        userInformationAccount.setFirstName(userInformationEntity.getFirstName());
        userInformationAccount.setLastName(userInformationEntity.getLastName());
        userInformationAccount.setDisplayPicture("thisisdisplaypicture");
        userInformationAccount.setRegDate(date);
        userInformationAccount.setRegId("sample");
        userInformationAccount.setUpdateId("sample");
        userInformationAccount.setUpdateDate(date);
        userInformationAccount.setDeleteFlg(false);
        userInformationAccountLogic.saveUserInformationAccount(userInformationAccount);
    }

    @Override
    public UserInformationInOutDto getUserDetailsUsingUsername(String username) {
        UserInformationInOutDto userInformationInOutDto = new UserInformationInOutDto();
        UserInformationObj userInformationObj = new UserInformationObj();
        UserInformation userInformation = userInformationLogic.matchedLoginCredentialsUsingUsername(username);

        if(userInformation == null) {
            return userInformationInOutDto;
        }
        userInformationObj.setEmail(userInformation.getEmail());

        userInformationInOutDto.setUserInformationObj(userInformationObj);
        return userInformationInOutDto;
    }

    @Override
    public UserInformationInOutDto getUserAndTheirCompletedGames(UserInformationInOutDto inDto) {
        UserInformationInOutDto outDto = new UserInformationInOutDto();

        List<UserInformationObj> userInformationObjList = new ArrayList<UserInformationObj>();
        List<Object[]> completeQuizzesOfUsers = saveGameInfoLogic.getAllCompletedQuizzesPerUser();
        for(Object[] objects: completeQuizzesOfUsers) {
            UserInformationObj userInformationObj = new UserInformationObj();

            userInformationObj.setId((Integer) objects[0]);

            userInformationObj.setUsername((String) objects[1]);

            userInformationObj.setCompletedQuizzes((Long) objects[2]);

            userInformationObjList.add(userInformationObj);

        }
        outDto.setStudentObjList(userInformationObjList);

        return outDto;

    }


}
