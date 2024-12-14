package com.example.demo.obj;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class UserInformationObj {
    private int id;

    private String encryptedIdPk;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dob;

    private int age;

    private String displayPicture;

    private String username;

    private Long completedQuizzes;

    private Timestamp registrationDate;
}
