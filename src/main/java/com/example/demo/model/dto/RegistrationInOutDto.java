package com.example.demo.model.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class RegistrationInOutDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private boolean isUpdate;

    private boolean isValid;

    private List<String> responseRegErrors;

    private boolean isSuccess;

    private Map<String,List<String>> errorlist;

    private MultipartFile image;
}
