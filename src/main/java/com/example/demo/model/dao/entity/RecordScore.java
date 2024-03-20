package com.example.demo.model.dao.entity;


import com.example.demo.model.dao.id.RecordScoreId;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="t_record_score")
@Data
public class RecordScore {

    @Id
    @Column(name = "record_id_tag",nullable = false)
    private String recordIdTag;

    @Column(name = "record_user_id_pk",nullable = false)
    private int recordUserIdPk;

    @Column(name = "record_quiz_id_pk",nullable = false)
    private int recordQuizIdPk;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    @Column(name = "user_answer",nullable = false)
    private String userAnswer;

    @Column(name="reg_id",nullable = false)
    private int regId;

    @Column(name = "reg_date", nullable = false)
    private Timestamp regDate;

    @Column(name = "delete_flg", nullable = false)
    private boolean deleteFlg;
}
