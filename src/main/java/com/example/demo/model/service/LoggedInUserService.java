package com.example.demo.model.service;

import com.example.demo.model.dto.UserInformationInOutDto;

import java.sql.SQLException;

public interface LoggedInUserService {
    public UserInformationInOutDto getLoggedInUserDetails() ;
}
