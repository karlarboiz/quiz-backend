package com.example.demo.annotation.impl;

import com.example.demo.annotation.DuplicateMailAddress;
import com.example.demo.annotation.DuplicateUserId;
import com.example.demo.model.logic.UserInformationLogic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateUserIdImpl implements ConstraintValidator<DuplicateUserId, String> {

    @Autowired
    private UserInformationLogic userInformationLogic;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext){

        return true;
    }

}
