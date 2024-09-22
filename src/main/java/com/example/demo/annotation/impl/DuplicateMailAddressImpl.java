package com.example.demo.annotation.impl;

import com.example.demo.annotation.DuplicateMailAddress;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.UserInformationLogic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateMailAddressImpl implements ConstraintValidator<DuplicateMailAddress, String> {
    @Autowired
    private UserInformationLogic userInformationLogic;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext){
        UserInformation userInformation = userInformationLogic.matchedLoginCredentials(value);
        return userInformation == null;
    }

}
