package com.example.demo.model.service.impl;

import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.LoggedInUserService;
import com.example.demo.obj.UserInformationObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LoggedInUserServiceImpl implements LoggedInUserService {

    @Autowired
    private UserInformationLogic userInformationLogic;

    @Override
    public UserInformationInOutDto getLoggedInUserDetails() {

        UserInformationInOutDto inOutDto = new UserInformationInOutDto();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInformationObj userInformationObj = new UserInformationObj();
        String username = authentication.getName();

        List<Object[]> userInformation = userInformationLogic.matchedLoginCredentialsUsingUsername(username);


        userInformationObj.setId((Integer) userInformation.get(0)[0]);
        userInformationObj.setEmail((String) userInformation.get(0)[2]);

        inOutDto.setUserInformationObj(userInformationObj);

        return inOutDto;
    }

}
