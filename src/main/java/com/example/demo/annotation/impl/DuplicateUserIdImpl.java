package com.example.demo.annotation.impl;

import com.example.demo.annotation.DuplicateMailAddress;
import com.example.demo.annotation.DuplicateUserId;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DuplicateUserIdImpl implements ConstraintValidator<DuplicateUserId, String> {

    @Autowired
    private UserInformationLogic userInformationLogic;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext){
        List<UserInformation> userInformationList = userInformationLogic.getUserInfoByUsername(value);

        System.out.println("Hello there");
        
        return userInformationList.size() > 0;
    }

}
