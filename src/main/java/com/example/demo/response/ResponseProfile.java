package com.example.demo.response;


import lombok.Data;

@Data
public class ResponseProfile {

    private String encryptedIdPk;

    private String username;

    private boolean isAuth;

    private String message;
}
