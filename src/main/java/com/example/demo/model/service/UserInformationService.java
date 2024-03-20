package com.example.demo.model.service;

import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;

import java.sql.SQLException;

public interface UserInformationService {
    public void saveUserInformation(RegistrationInOutDto userInformationEntity);

    public UserInformationInOutDto getUserDetailsUsingUsername(String username);

}
