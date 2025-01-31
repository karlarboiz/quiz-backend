package com.example.demo.annotation.impl;

import com.example.demo.annotation.NonExistentCredentials;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.logic.impl.UserInformationLogicImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class NonExistentCredentialsImpl implements ConstraintValidator<NonExistentCredentials, String> {

    @Autowired
    private UserInformationLogicImpl userInformationLogic;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {


        try {
            if(value == null || value.isEmpty()) {
                return true;
            }
            UserInformation userInformation = userInformationLogic.matchedLoginCredentials(value);

            System.out.println(userInformation);
            return userInformation == null;
        }catch (NullPointerException e){
            System.out.print(e.getMessage());
        }

        return true;
    }
}
