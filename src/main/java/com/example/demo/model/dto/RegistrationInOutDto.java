package com.example.demo.model.dto;


import lombok.Data;

@Data
public class RegistrationInOutDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private boolean isUpdate;
}
