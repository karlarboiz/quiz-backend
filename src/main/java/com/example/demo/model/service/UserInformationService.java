package com.example.demo.model.service;

import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.dto.UserInformationInOutDto;

import java.io.IOException;

public interface UserInformationService {

    public RegistrationInOutDto validateUserRegistration(RegistrationInOutDto registrationInOutDto);

    public SavingDto saveUserInformation(RegistrationInOutDto userInformationEntity) throws IOException;

    public UserInformationInOutDto getUserDetailsUsingUsername(String username);

    public UserInformationInOutDto getUserAndTheirCompletedGames(UserInformationInOutDto outDto);

}
