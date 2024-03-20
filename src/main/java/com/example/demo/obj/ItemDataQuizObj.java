package com.example.demo.obj;


import lombok.Data;

@Data
public class ItemDataQuizObj {
    private Long id;

    private int quizItemIdPk;

    private int quizUserIdPk;

    private String quizIdTag;

    private boolean answered;

    private int incrementId;

    private String correctAnswer;

    private boolean isCorrect;

    private String userAnswer;

}


