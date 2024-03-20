package com.example.demo.model.dao.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="m_save_game_info")
@Data
public class SaveGameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pk",nullable = false)
    private int idPk;

    @Column(name = "quiz_uuid", nullable = false)
    private String quizUUID;

    @Column(name="user_id_pk",nullable = false)
    private int userIdPk;

    @Column(name="date",nullable = false)
    private String date;


    @Column(name="completed",nullable = false)
    private boolean completed;

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
