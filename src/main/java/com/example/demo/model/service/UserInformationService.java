package com.example.demo.model.service;

import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.dto.UserInformationInOutDto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface UserInformationService {

    public RegistrationInOutDto validateUserRegistration(RegistrationInOutDto registrationInOutDto);

    public SavingDto saveUserInformation(RegistrationInOutDto userInformationEntity) throws IOException;

    public UserInformationInOutDto getUserDetailsUsingUsername(String username) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    public UserInformationInOutDto getUserAndTheirCompletedGames(UserInformationInOutDto outDto);

}
