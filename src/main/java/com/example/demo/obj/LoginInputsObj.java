package com.example.demo.obj;

import com.example.demo.annotation.NonExistentCredentials;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

@Data
public class LoginInputsObj {

    @Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Not a valid email")
    @Length(min = 10, max = 30, message = "Email must be between 10 - 30 characters")
    @NonExistentCredentials
    private String email;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,15}$",message = "Not a valid password")
    @Length(min = 7, max = 15, message = "Password must be between 7 - 15 characters")
    private String password;

}
