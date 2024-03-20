package com.example.demo.model.service.impl;

import com.example.demo.model.dao.entity.ItemDataQuiz;
import com.example.demo.model.dto.ItemDataQuizInOutDto;
import com.example.demo.model.logic.ItemDataQuizLogic;
import com.example.demo.model.service.ItemDataQuizService;
import com.example.demo.obj.ItemDataQuizObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class ItemDataQuizServiceImpl implements ItemDataQuizService {

    @Autowired
    private ItemDataQuizLogic itemDataQuizLogic;

    @Override
    public void saveItemDataQuiz(ItemDataQuizInOutDto inOutDto) {
        String quizIdTag = inOutDto.getQuizIdTag();
        int userIdPk = inOutDto.getQuizUserIdPk();
        int itemIdPk = inOutDto.getSaveQuizInfoIdPk();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ItemDataQuiz itemDataQuiz = itemDataQuizLogic.getItemBasedOnQuizIdTagAndQuizUserIdPkAndQuizItemIdPk(quizIdTag,userIdPk,itemIdPk);

        itemDataQuiz.setAnswered(true);
        itemDataQuiz.setUpdateDate(timestamp);
        itemDataQuiz.setUpdateId(inOutDto.getQuizUserIdPk());

        itemDataQuizLogic.saveItemDataQuiz(itemDataQuiz);
    }

    @Override
    public void saveListOfItemDataQuiz(List<ItemDataQuizInOutDto> itemDataQuizInOutDtoList){
        List<ItemDataQuiz> itemDataQuizList = new ArrayList<>();
        for(ItemDataQuizInOutDto inOutDto: itemDataQuizInOutDtoList) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ItemDataQuiz itemDataQuiz = new ItemDataQuiz();
            itemDataQuiz.setQuizItemIdPk(inOutDto.getSaveQuizInfoIdPk());
            itemDataQuiz.setQuizUserIdPk(inOutDto.getQuizUserIdPk());
            itemDataQuiz.setQuizIdTag(inOutDto.getQuizIdTag());
            itemDataQuiz.setCorrectAnswer(inOutDto.getCorrectAnswer());
            itemDataQuiz.setIncrementId(inOutDto.getIncrementId());
            itemDataQuiz.setAnswered(false);
            itemDataQuiz.setRegDate(timestamp);
            itemDataQuiz.setRegId(inOutDto.getQuizUserIdPk());
            itemDataQuiz.setUpdateDate(timestamp);
            itemDataQuiz.setUpdateId(inOutDto.getQuizUserIdPk());
            itemDataQuiz.setDeleteFlg(false);

            itemDataQuizList.add(itemDataQuiz);
        }

        itemDataQuizLogic.saveListItemDataQuiz(itemDataQuizList);

    }

    @Override
    public ItemDataQuizInOutDto getListOfQuizItems(ItemDataQuizInOutDto itemDataQuizInOutDto){
        ItemDataQuizInOutDto outDto = new ItemDataQuizInOutDto();
        int itemIdPk = itemDataQuizInOutDto.getSaveQuizInfoIdPk();
        int userIdPk = itemDataQuizInOutDto.getQuizUserIdPk();
        List<ItemDataQuizObj> itemDataQuizObjList = new ArrayList<>();

        List<ItemDataQuiz> itemDataQuizList = itemDataQuizLogic.getListOfItemsBasesOnGameInfoIdAndUserId(itemIdPk,userIdPk);

        for(ItemDataQuiz itemDataQuiz : itemDataQuizList) {
            ItemDataQuizObj itemDataQuizObj = new ItemDataQuizObj();

            itemDataQuizObj.setId(itemDataQuiz.getId());

            itemDataQuizObj.setQuizIdTag(itemDataQuiz.getQuizIdTag());

            itemDataQuizObj.setAnswered(itemDataQuiz.isAnswered());

            itemDataQuizObjList.add(itemDataQuizObj);
        }
        outDto.setItemDataQuizObjList(itemDataQuizObjList);

        return outDto;
    }

    @Override
    public ItemDataQuizInOutDto getListOfAnsweredQuizItems(ItemDataQuizInOutDto itemDataQuizInOutDto){
        ItemDataQuizInOutDto outDto = new ItemDataQuizInOutDto();
        int itemIdPk = itemDataQuizInOutDto.getSaveQuizInfoIdPk();
        int userIdPk = itemDataQuizInOutDto.getQuizUserIdPk();
        List<ItemDataQuizObj> itemDataQuizObjList = new ArrayList<>();

        List<ItemDataQuiz> itemDataQuizList = itemDataQuizLogic.getListOfItemsBasesOnGameInfoIdAndUserId(itemIdPk,userIdPk);

        for(ItemDataQuiz itemDataQuiz : itemDataQuizList) {
            if(itemDataQuiz.isAnswered()){
                ItemDataQuizObj itemDataQuizObj = new ItemDataQuizObj();

                itemDataQuizObj.setId(itemDataQuiz.getId());

                itemDataQuizObj.setQuizIdTag(itemDataQuiz.getQuizIdTag());

                itemDataQuizObj.setAnswered(itemDataQuiz.isAnswered());

                itemDataQuizObjList.add(itemDataQuizObj);
            }
        }
        outDto.setItemDataQuizObjList(itemDataQuizObjList);

        return outDto;
    }

    @Override
    public ItemDataQuizInOutDto getListOfAnswerQuizItemsBasedOnUserIdPkAndItemIdPk(ItemDataQuizInOutDto itemDataQuizInOutDto){
        ItemDataQuizInOutDto outDto = new ItemDataQuizInOutDto();

        int itemIdPk = itemDataQuizInOutDto.getSaveQuizInfoIdPk();
        int userIdPk = itemDataQuizInOutDto.getQuizUserIdPk();

        List<ItemDataQuizObj> itemDataQuizObjList = new ArrayList<>();

        List<Object[]> quizInfoList = itemDataQuizLogic.getAnsweredQuizInfoListBasedOnUserIdPkAndItemIdPk(userIdPk,itemIdPk);

        for(Object[] item: quizInfoList) {


            ItemDataQuizObj itemDataQuizObj = new ItemDataQuizObj();
            itemDataQuizObj.setQuizIdTag((String) item[0]);
            itemDataQuizObj.setIncrementId((Integer) item[1]);

            itemDataQuizObj.setCorrectAnswer((String) item[2]);

            itemDataQuizObj.setCorrect((boolean) item[3]);

            itemDataQuizObj.setUserAnswer((String) item[4]);

            itemDataQuizObjList.add(itemDataQuizObj);
        }

        outDto.setItemDataQuizObjList(itemDataQuizObjList);

        return outDto;
    }
}
