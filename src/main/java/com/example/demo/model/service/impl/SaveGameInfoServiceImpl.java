package com.example.demo.model.service.impl;

import com.example.demo.model.dao.entity.SaveGameInfo;
import com.example.demo.model.dto.SaveGameInfoInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.logic.SaveGameInfoLogic;
import com.example.demo.model.service.SaveGameInfoService;
import com.example.demo.obj.SaveGameInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class SaveGameInfoServiceImpl implements SaveGameInfoService {
    @Autowired
    private SaveGameInfoLogic saveGameInfoLogic;
    @Override
    public SaveGameInfoInOutDto saveGameInformation(SaveGameInfoInOutDto inDto){

        SaveGameInfoInOutDto saveGameInfoInOutDto = new SaveGameInfoInOutDto();
        if(!inDto.isUpdateSaveGameInfo()){
            LocalDate now = LocalDate.now();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            int userIdPk = inDto.getUserInformationObj().getId();

            SaveGameInfo saveGameInfo = new SaveGameInfo();

            saveGameInfo.setQuizUUID(inDto.getQuizUUID());

            saveGameInfo.setUserIdPk(userIdPk);

            saveGameInfo.setDate(now.toString());

            saveGameInfo.setCompleted(false);

            saveGameInfo.setRegId(userIdPk);

            saveGameInfo.setRegDate(timestamp);

            saveGameInfo.setUpdateId(userIdPk);

            saveGameInfo.setUpdateDate(timestamp);

            saveGameInfo.setDeleteFlg(false);

            saveGameInfoLogic.saveGameInfoLogic(saveGameInfo);
        }

        return saveGameInfoInOutDto;

    }


    @Override
    public SaveGameInfoInOutDto findLatestSaveGameInfo() {
        SaveGameInfoInOutDto outDto = new SaveGameInfoInOutDto();

        SaveGameInfo saveGameInfo = saveGameInfoLogic.findLatestSaveGameInfo();

        outDto.setIdPk(saveGameInfo.getIdPk());

        return  outDto;
    }

    @Override
    public void updateSaveGameInfo(SaveGameInfoInOutDto inOutDto){
        int saveGameInfoIdPk = inOutDto.getIdPk();

        SaveGameInfo saveGameInfo = saveGameInfoLogic.updateSaveGameInfoIdPk(saveGameInfoIdPk);

        saveGameInfo.setCompleted(inOutDto.isUpdateSaveGameInfo());

        saveGameInfoLogic.saveGameInfoLogic(saveGameInfo);
    }


    @Override
    public SaveGameInfoInOutDto getAllDetailsOfSavedGames(SaveGameInfoInOutDto inDto){

        int userIdPk = inDto.getUserIdPk();
        SaveGameInfoInOutDto saveGameInfoInOutDto = new SaveGameInfoInOutDto();

        List<SaveGameInfoObj> saveGameInfoObjs = new ArrayList<>();

        List<Object[]> saveGameObjList = saveGameInfoLogic.getAllCompletedActiveSaveGameInfoOfAUser(userIdPk);

        for(Object[] item: saveGameObjList){
            SaveGameInfoObj saveGameInfoObj = new SaveGameInfoObj();

            saveGameInfoObj.setIdPk((Integer) item[0]);
            saveGameInfoObj.setQuizUUID((String) item[1]);
            saveGameInfoObj.setUserIdPk((Integer) item[2]);
            saveGameInfoObj.setTotalCorrect((Integer) item[3]);
            saveGameInfoObj.setIdPk((Integer) item[4]);

            saveGameInfoObjs.add(saveGameInfoObj);
        }

        saveGameInfoInOutDto.setSavedGameDetails(saveGameInfoObjs);

        return saveGameInfoInOutDto;

    }




}
