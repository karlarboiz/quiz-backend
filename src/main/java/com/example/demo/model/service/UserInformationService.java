package com.example.demo.model.service;

import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.dto.UserInformationInOutDto;

public interface UserInformationService {

    public RegistrationInOutDto validateUserRegistration(RegistrationInOutDto registrationInOutDto);

    public SavingDto saveUserInformation(RegistrationInOutDto userInformationEntity);

    public UserInformationInOutDto getUserDetailsUsingUsername(String username);

    public UserInformationInOutDto getUserAndTheirCompletedGames(UserInformationInOutDto outDto);

}
