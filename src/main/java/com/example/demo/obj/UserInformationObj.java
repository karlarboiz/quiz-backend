package com.example.demo.obj;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInformationObj {
    private int id;


    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dob;

    private int age;


}
