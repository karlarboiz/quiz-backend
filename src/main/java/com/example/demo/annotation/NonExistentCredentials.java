package com.example.demo.annotation;


import com.example.demo.annotation.impl.DuplicateUserIdImpl;
import com.example.demo.annotation.impl.NonExistentCredentialsImpl;
import jakarta.validation.Constraint;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonExistentCredentialsImpl.class)
public @interface NonExistentCredentials {

    String message() default "Credentials don't exist in the records.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
