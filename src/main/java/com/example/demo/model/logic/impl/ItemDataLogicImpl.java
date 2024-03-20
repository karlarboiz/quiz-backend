package com.example.demo.model.logic.impl;

import com.example.demo.model.dao.ItemDataQuizDao;
import com.example.demo.model.dao.entity.ItemDataQuiz;
import com.example.demo.model.logic.ItemDataQuizLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemDataLogicImpl implements ItemDataQuizLogic {

    @Autowired
    private ItemDataQuizDao itemDataQuizDao;


    @Override
    public void saveItemDataQuiz(ItemDataQuiz itemDataQuiz){


        itemDataQuizDao.save(itemDataQuiz);
    }


    @Override
    public void saveListItemDataQuiz(List<ItemDataQuiz> itemDataQuizList){
        itemDataQuizDao.saveAll(itemDataQuizList);
    }

    @Override
    public List<ItemDataQuiz> getListOfItemsBasesOnGameInfoIdAndUserId(int itemIdPk, int userIdPk) {
        return itemDataQuizDao.getListOfItemsBasesOnGameInfoIdAndUserId(itemIdPk,userIdPk);
    }

    @Override
    public ItemDataQuiz getItemBasedOnQuizIdTagAndQuizUserIdPkAndQuizItemIdPk(String quizIdTag,
                                                                              int quizUserIdPk,int quizItemIdPk) {
        return  itemDataQuizDao.getItemBasedOnQuizIdTagAndQuizUserIdPkAndQuizItemIdPk(quizIdTag,quizUserIdPk,quizItemIdPk);
    }

    @Override
    public List<Object[]> getAnsweredQuizInfoListBasedOnUserIdPkAndItemIdPk(int userIdPk, int itemIdPk) {
        return itemDataQuizDao.getAnsweredQuizInfoListBasedOnUserIdPkAndItemIdPk(userIdPk,itemIdPk);
    }
}
