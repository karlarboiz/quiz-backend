package com.example.demo.model.dao;

import com.example.demo.model.dao.entity.RecordScore;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordScoreDao extends JpaRepository<RecordScore, String> {


    final String GET_RECORD_QUIZ_LIST =
            " select e from RecordScore e " +
                    " where e.recordQuizIdPk = :itemIdPk " +
                    " and e.recordUserIdPk = :userIdPk " +
                    " and e.deleteFlg = false ";


    @Query(value = GET_RECORD_QUIZ_LIST)
    public List<RecordScore> getRecordQuizList(int itemIdPk, int userIdPk) throws DataAccessException;
}
