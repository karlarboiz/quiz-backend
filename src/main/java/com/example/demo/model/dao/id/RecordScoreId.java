package com.example.demo.model.dao.id;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

public class RecordScoreId implements Serializable {

    private int recordUserIdPk;
    private int recordQuizIdPk;
}
