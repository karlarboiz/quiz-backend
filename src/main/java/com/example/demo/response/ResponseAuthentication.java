package com.example.demo.response;


import lombok.Data;

import java.util.List;

@Data
public class ResponseAuthentication {
    private boolean isValid;
    private String token;
    private String message;

    private List<String> responseAuthErrors;
}
