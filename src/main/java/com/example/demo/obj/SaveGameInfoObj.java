package com.example.demo.obj;


import lombok.Data;

import java.math.BigInteger;

@Data
public class SaveGameInfoObj {

    private int idPk;

    private String encryptedIdPk;

    private String decryptedIdPk;

    private String quizUUID;

    private int userIdPk;

    private Long totalCorrect;

    private Long totalIncorrect;

    private Long totalQuizItems;

    private String userName;

    private Long completedQuizzes;

    private Long incompleteQuizzes;

    private String date;
}
