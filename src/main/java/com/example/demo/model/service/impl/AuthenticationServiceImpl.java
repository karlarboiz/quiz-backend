package com.example.demo.model.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.AuthenticationService;

import com.example.demo.obj.LoginInputsObj;
import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.*;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserInformationLogic userInformationLogic;

    @Autowired
    private JwtService jwtService;

    private final Validator validator;

    // Injecting the LocalValidatorFactoryBean using the @Qualifier annotation

    public AuthenticationServiceImpl(@Qualifier("localValidatorFactoryBean") Validator validator) {

        this.validator = validator;

    }

    /**
     * validate login request
     * email and password inputs
     * */
    @Override
    public ResponseAuthentication validateResponseAuthentication(RequestAuthentication requestAuthentication) {


        ResponseAuthentication responseAuthentication = new ResponseAuthentication();


        LoginInputsObj loginInputsObj = new LoginInputsObj();

        loginInputsObj.setEmail(requestAuthentication.getEmail());

        loginInputsObj.setPassword(requestAuthentication.getPassword());
        //get errors

        Set<ConstraintViolation<LoginInputsObj>> violations = validator.validate(loginInputsObj);

        List<String> errorMsgs = new ArrayList<>();

        for(ConstraintViolation item: violations) {
            errorMsgs.add(item.getMessage());
        }
        responseAuthentication.setValid(!errorMsgs.isEmpty());

        responseAuthentication.setResponseAuthErrors(errorMsgs);
        return responseAuthentication;
    }



    @Override
    public ResponseAuthentication responseAuthentication(RequestAuthentication requestAuthentication) {

        ResponseAuthentication responseAuthentication = new ResponseAuthentication();

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestAuthentication.getEmail(),
                            requestAuthentication.getPassword()
                    )
            );

            UserInformation userInformation = userInformationLogic.matchedLoginCredentials(requestAuthentication.getEmail());
            if(userInformation == null) {
                responseAuthentication.setValid(false);
                responseAuthentication.setToken(null);

            }else {
                String jwtToken =  jwtService.generateToken(userInformation);
                responseAuthentication.setValid(true);
                responseAuthentication.setToken(jwtToken);
            }
        }catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            responseAuthentication.setMessage("Credentials not recognized. Please try again!");
        }
        return responseAuthentication;


    }


}
