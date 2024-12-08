package com.example.demo.model.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.AuthenticationService;

import com.example.demo.obj.LoginInputsObj;
import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserInformationLogic userInformationLogic;

    @Autowired
    private JwtService jwtService;


    /**
     * validate login request
     * email and password inputs
     * */
    @Override
    public ResponseAuthentication validateResponseAuthentication(RequestAuthentication requestAuthentication) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ResponseAuthentication responseAuthentication = new ResponseAuthentication();

        LoginInputsObj loginInputsObj = new LoginInputsObj();

        loginInputsObj.setEmail(requestAuthentication.getEmail());

        loginInputsObj.setPassword(requestAuthentication.getPassword());
        //get errors

        Set<ConstraintViolation<LoginInputsObj>> violations = validator.validate(loginInputsObj);

        List<String> errorMsgs = new ArrayList<>();

        List<Map<String, List<String>> >errorlist = new ArrayList<>();

        List<String> emailErrorList = new ArrayList<>();

        List<String> passwordErrorList = new ArrayList<>();

        Map<String,List<String>> collectionError = new HashMap<>();

        for(ConstraintViolation item: violations) {

            if(item.getPropertyPath().toString().matches("email")){

                emailErrorList.add(item.getMessage());

            }else {
                passwordErrorList.add(item.getMessage());
            }
            errorMsgs.add(item.getMessage());
        }
        collectionError.put("email",emailErrorList);
        collectionError.put("password",passwordErrorList);

        responseAuthentication.setValid(!errorMsgs.isEmpty());

        responseAuthentication.setResponseAuthErrors(errorMsgs);

        responseAuthentication.setErrorlist(collectionError);
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
