package com.example.demo.obj;


import lombok.Data;

import java.math.BigInteger;

@Data
public class SaveGameInfoObj {

    private int idPk;

    private String quizUUID;

    private int userIdPk;

    private Long totalCorrect;

    private Long totalIncorrect;

    private Long totalQuizItems;
}
