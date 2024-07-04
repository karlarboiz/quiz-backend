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
            "select " +
                    " e.idPk, e.quizUUID, " +
                    " e.userIdPk, " +
                    " case " +
                    " when e.completed then ( " +
                    "    select count(f.recordQuizIdPk) from RecordScore f " +
                    "    where f.isCorrect = true " +
                    " and f.recordQuizIdPk = e.idPk )" +
                    "    else 0 " +
                    "    end as totalCorrect " +
                    "    ," +
                    " case " +
                    " when e.completed then ( " +
                    "    select count(f.recordQuizIdPk) from RecordScore f" +
                    "    where f.isCorrect = false " +
                    " and f.recordQuizIdPk = e.idPk)" +
                    "    else 0 " +
                    "    end as totalIncorrect, " +
                    " (select count(t.id) from ItemDataQuiz t " +
                    " where t.quizUserIdPk = e.userIdPk and " +
                    " t.quizItemIdPk = e.idPk ) as totalQuizItems" +
                    " from SaveGameInfo e " +
                    " where e.userIdPk = :userIdPk " +
                    " and e.deleteFlg = false ";
    final String GET_ALL_COMPlETED_QUIZZES_PER_USER =
            "SELECT e.id_pk,e.username, e.reg_date," +
                    "    IFNULL(completed_quizzes.count_completed, 0) AS something_now " +
                    "FROM m_user_information e " +
                    "LEFT JOIN ( " +
                    "    SELECT g.user_id_pk, COUNT(g.user_id_pk) AS count_completed " +
                    "    FROM m_save_game_info g " +
                    "    WHERE g.completed = true " +
                    "    GROUP BY g.user_id_pk " +
                    ") AS completed_quizzes ON e.id_pk = completed_quizzes.user_id_pk " +
                    "INNER JOIN m_user_information_account f ON e.id_pk = f.user_id_pk " +
                    "WHERE e.delete_flg = false AND f.delete_flg = false ";
    final String GET_ALL_NOT_COMPLETED_QUIZZES =
            " SELECT e.id_pk, e.date, e.quiz_uuid, " +
                    "    IFNULL(incomplete_quizzes.count_incomplete, 0) AS items_unanswered " +
                    "FROM m_save_game_info e " +
                    "LEFT JOIN ( " +
                    "    SELECT g.quiz_item_id_pk, COUNT(g.id) AS count_incomplete " +
                    "    FROM t_item_data_quiz g" +
                    "    where g.answered = false " +
                    "    AND g.delete_flg = false " +
                    "    GROUP BY g.quiz_item_id_pk " +
                    ") AS incomplete_quizzes ON e.id_pk = incomplete_quizzes.quiz_item_id_pk " +
                    "WHERE e.user_id_pk = :userIdPk " +
                    "AND e.completed = false " +
                    "AND e.delete_flg = false ";
    @Query(value = FIND_LATEST_GAME_INFO)
    public SaveGameInfo findLatestGameInfo() throws DataException;

    @Query(value = UPDATE_SAVE_GAME_INFO_BASED_ON_ID_PK)
    public SaveGameInfo updateSaveGameInfoBasedOnIdPk(int idPk) throws DataException;

    @Query(value = GET_ALL_COMPLETED_ACTIVE_SAVE_GAME_INFO_OF_A_USER)
    public List<Object[]> getAllCompletedActiveSaveGameInfoOfAUser(int userIdPk) throws DataAccessException;
    
    
    @Query(value =GET_ALL_COMPlETED_QUIZZES_PER_USER,nativeQuery = true )
    public List<Object[]> getAllCompletedQuizzesPerUser() throws DataAccessException;

    @Query(value=GET_ALL_NOT_COMPLETED_QUIZZES ,nativeQuery = true)
    public List<Object[]> getAllNotCompletedQuizzes(int userIdPk) throws DataAccessException;


    @Query(value = UPDATE_SAVE_GAME_INFO_BASED_ON_ID_PK)
    public SaveGameInfo findSaveGameInfoBasedOnIdPk(int idPk) throws  DataAccessException;

}
