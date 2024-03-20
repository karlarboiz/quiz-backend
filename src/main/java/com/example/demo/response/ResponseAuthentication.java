package com.example.demo.response;


import lombok.Data;

@Data
public class ResponseAuthentication {
    private boolean isValid;
    private String token;
    private String message;
}
