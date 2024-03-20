package com.example.demo.model.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Entity
@Table(name="m_user_information_account")
@Data
public class UserInformationAccount {
    @Id
    @Column(name="user_id_pk",nullable = false)
    private int userIdPk;

    @Length(min = 2, max = 255)
    @Column(name="first_name",nullable = false,length = 255)
    private String firstName;

    @Length(min = 2, max = 255)
    @Column(name = "last_name",nullable = false,length=255)
    private String lastName;

    @Length(min=7,max = 100)
    @Column(name = "display_picture",nullable = false,length = 100)
    private String displayPicture;


    @Column(name = "reg_id",nullable = false)
    private String regId;

    @Column(name="reg_date",nullable = false)
    private Timestamp regDate;

    @Column(name="update_id",nullable = false)
    private String updateId;

    @Column(name="update_date",nullable = false)
    private Timestamp updateDate;

    @Column(name="delete_flg", nullable = false)
    private boolean deleteFlg = false;
}
