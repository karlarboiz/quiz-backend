package com.example.demo.response;


import lombok.Data;

@Data
public class ForgotPasswordResponse {


    public String message;

    public Boolean isEmailSent;
}
