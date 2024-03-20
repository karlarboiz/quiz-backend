package com.example.demo.response;


import lombok.Data;

@Data
public class ResponseMessage<T> {
    private boolean success;

    private boolean isAuth;

    private String message;

}
