package com.example.demo.model.dao.entity;


import com.example.demo.model.dao.id.ItemDataQuizId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.sql.Timestamp;

@Entity
@Table(name="t_item_data_quiz")
@Data
public class ItemDataQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Auto-incrementing primary key

    @Column(name = "quiz_item_id_pk",nullable = false)
    private int quizItemIdPk;

    @Column(name = "quiz_user_id_pk",nullable = false)
    private int quizUserIdPk;

    @Column(name="quiz_id_tag",nullable = false)
    private String quizIdTag;

    @Column(name = "answered", nullable = false)
    private boolean answered;

    @Column(name="correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name="increment_id", nullable = false)
    private int incrementId;

    @Column(name="reg_id",nullable = false)
    private int regId;

    @Column(name = "reg_date", nullable = false)
    private Timestamp regDate;

    @Column(name="update_id",nullable = false)
    private int updateId;

    @Column(name = "update_date", nullable = false)
    private Timestamp updateDate;

    @Column(name = "delete_flg", nullable = false)
    private boolean deleteFlg;

}
