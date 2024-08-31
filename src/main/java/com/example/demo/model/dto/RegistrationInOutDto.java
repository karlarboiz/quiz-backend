package com.example.demo.model.dto;


import lombok.Data;

import java.util.List;

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


}
