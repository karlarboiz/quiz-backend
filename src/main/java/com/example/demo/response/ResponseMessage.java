package com.example.demo.response;


import lombok.Data;

@Data
public class ResponseMessage {
    private boolean success;

    private boolean isAuth;

    private String message;

}
