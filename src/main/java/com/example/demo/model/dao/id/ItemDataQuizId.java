package com.example.demo.model.dao.id;


import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Data
@Embeddable
public class ItemDataQuizId implements Serializable {

    private int quizItemIdPk;

    private int quizUserIdPk;

}
