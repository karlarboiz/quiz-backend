package com.example.demo.annotation;

import com.example.demo.annotation.impl.DuplicateMailAddressImpl;
import com.example.demo.annotation.impl.DuplicateUserIdImpl;
import jakarta.validation.Constraint;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateUserIdImpl.class)
public @interface DuplicateUserId {

    String message() default "User Id has duplicates.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
