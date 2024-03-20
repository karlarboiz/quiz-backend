package com.example.demo.model.logic;

import com.example.demo.model.dao.entity.RecordScore;

import java.util.List;

public interface RecordScoreLogic {
    public void saveRecordScoreLogic(RecordScore recordScore);

    public List<RecordScore> getRecordQuizLIst(int itemIdPk, int userIdPk);
}
