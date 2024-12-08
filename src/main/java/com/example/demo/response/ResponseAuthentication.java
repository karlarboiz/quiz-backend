package com.example.demo.response;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResponseAuthentication {
    private boolean isValid;
    private String token;
    private String message;

    private List<String> responseAuthErrors;
    private Map<String, List<String>> errorlist;
}
