package com.example.demo.annotation;


import com.example.demo.annotation.impl.DuplicateMailAddressImpl;
import jakarta.validation.Constraint;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateMailAddressImpl.class)
public @interface DuplicateMailAddress {

    String message() default "Mail Address already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
