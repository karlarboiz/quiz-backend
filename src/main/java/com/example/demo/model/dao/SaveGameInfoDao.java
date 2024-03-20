package com.example.demo.model.dao;

import com.example.demo.model.dao.entity.SaveGameInfo;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaveGameInfoDao extends JpaRepository<SaveGameInfo,Integer> {
    final String FIND_LATEST_GAME_INFO =
            " SELECT e FROM SaveGameInfo e " +
                    " WHERE e.idPk = (SELECT max(f.idPk) " +
                    " FROM SaveGameInfo f ) ";
    final String UPDATE_SAVE_GAME_INFO_BASED_ON_ID_PK =
            " Select e from SaveGameInfo e " +
                    " where e.idPk = :idPk " +
                    " and e.deleteFlg = false ";

    final String GET_ALL_COMPLETED_ACTIVE_SAVE_GAME_INFO_OF_A_USER =
            " select e.idPk,e.quizUUID,e.userIdPk," +
                    " case " +
                    " when e.completed then ( " +
                    "     select count(f.recordQuizIdPk) from RecordScore f " +
                    "     where f.isCorrect = true " +
                    "     and f.recordQuizIdPk = e.idPk " +
                    "    ) " +
                    "    else 0 " +
                    "    end as totalCorrect, " +
                    " case " +
                    " when e.completed then ( " +
                    "     select count(f.recordQuizIdPk) from RecordScore f " +
                    "     where f.isCorrect = false " +
                    "     and f.recordQuizIdPk = e.idPk" +
                    "    ) " +
                    "    else 0 " +
                    "    end as totalIncorrect " +
                    " from SaveGameInfo e " +
                    " where e.userIdPk = :userIdPk " +
                    " and e.deleteFlg = false ";
    @Query(value = FIND_LATEST_GAME_INFO)
    public SaveGameInfo findLatestGameInfo() throws DataException;

    @Query(value = UPDATE_SAVE_GAME_INFO_BASED_ON_ID_PK)
    public SaveGameInfo updateSaveGameInfoBasedOnIdPk(int idPk) throws DataException;

    @Query(value = GET_ALL_COMPLETED_ACTIVE_SAVE_GAME_INFO_OF_A_USER)
    public List<Object[]> getAllCompletedActiveSaveGameInfoOfAUser(int userIdPk) throws DataAccessException;
}
