package com.example.demo.model.dao;

import com.example.demo.model.dao.entity.ItemDataQuiz;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDataQuizDao extends JpaRepository<ItemDataQuiz, Long> {
    final String GET_LIST_OF_QUIZ_ITEMS_BASED_ON_GAME_INFO_ID_AND_USER_ID =
            "select e from ItemDataQuiz e" +
                    " inner join SaveGameInfo f" +
                    " on e.quizItemIdPk  = :itemIdPk" +
                    " inner join UserInformation g" +
                    " on e.quizUserIdPk = :userIdPk" +
                    " and f.completed = false" +
                    " and e.deleteFlg = false" +
                    " and g.deleteFlg = false";


    final String GET_ITEM_DATA_QUIZ_BASED_ON_QUIZ_ID_TAG_AND_QUIZ_USER_ID_PK_AND_QUIZ_ITEM_ID_PK =
            "select e from ItemDataQuiz e " +
                    " where e.quizItemIdPk = :quizItemIdPk " +
                    " and e.quizUserIdPk = :quizUserIdPk " +
                    " and e.quizIdTag = :quizIdTag " +
                    " and e.deleteFlg = false";



    final String GET_ANSWERED_QUIZ_INFO_LIST_BASED_ON_USER_ID_PK_AND_ITEM_ID_PK =
            " select e.quizIdTag,  e.incrementId, e.correctAnswer," +
            " f.isCorrect, f.userAnswer from ItemDataQuiz e " +
            " inner join RecordScore f " +
            " on f.recordIdTag = e.quizIdTag " +
            " where e.answered = true " +
            " and e.quizItemIdPk = :itemIdPk " +
            " and e.quizUserIdPk = :userIdPk " +
            " and e.deleteFlg = false" +
            " and f.deleteFlg = false" ;


    @Query(value = GET_LIST_OF_QUIZ_ITEMS_BASED_ON_GAME_INFO_ID_AND_USER_ID)
    public List<ItemDataQuiz> getListOfItemsBasesOnGameInfoIdAndUserId(int itemIdPk, int userIdPk) throws DataAccessException;

    @Query(value = GET_ITEM_DATA_QUIZ_BASED_ON_QUIZ_ID_TAG_AND_QUIZ_USER_ID_PK_AND_QUIZ_ITEM_ID_PK)
    public ItemDataQuiz getItemBasedOnQuizIdTagAndQuizUserIdPkAndQuizItemIdPk(String quizIdTag,
                                                                              int quizUserIdPk,int quizItemIdPk) throws  DataAccessException;

    @Query(value = GET_ANSWERED_QUIZ_INFO_LIST_BASED_ON_USER_ID_PK_AND_ITEM_ID_PK)
    public  List<Object[]> getAnsweredQuizInfoListBasedOnUserIdPkAndItemIdPk(int userIdPk, int itemIdPk) throws  DataAccessException;
}
