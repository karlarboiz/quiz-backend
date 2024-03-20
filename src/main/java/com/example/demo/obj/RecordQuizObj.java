package com.example.demo.obj;

import lombok.Data;

@Data
public class RecordQuizObj {
    private String quizTag;

    private boolean isCorrect;

    private String userAnswer;

}
