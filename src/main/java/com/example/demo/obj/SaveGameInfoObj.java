package com.example.demo.obj;


import lombok.Data;

@Data
public class SaveGameInfoObj {

    private int idPk;

    private String quizUUID;

    private int userIdPk;

    private int totalCorrect;

    private int totalIncorrect;
}
