package com.example.demo.model.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserInformationLogic userInformationLogic;

    @Autowired
    private JwtService jwtService;



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
