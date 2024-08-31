package com.example.demo.annotation;


import com.example.demo.annotation.impl.RegistrationValidationImpl;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegistrationValidationImpl.class)
public @interface RegistrationValidation {
}
