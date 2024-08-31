package com.example.demo.annotation.impl;


import com.example.demo.annotation.RegistrationValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegistrationValidationImpl implements ConstraintValidator<RegistrationValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext){

        return true;
    }

}