package com.example.demo.model.service.impl;

import com.example.demo.model.dao.entity.SaveGameInfo;
import com.example.demo.model.dto.SaveGameInfoInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.logic.SaveGameInfoLogic;
import com.example.demo.model.service.SaveGameInfoService;
import com.example.demo.obj.SaveGameInfoObj;
import com.example.demo.util.ExtendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    public SaveGameInfoInOutDto findSaveGameInfoBasedOnIdPk(SaveGameInfoInOutDto inDto) {
        SaveGameInfoInOutDto outDto = new SaveGameInfoInOutDto();

        SaveGameInfo saveGameInfo = saveGameInfoLogic.findSaveGameInfoBasedOnIdPk(inDto.getIdPk());

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
            saveGameInfoObj.setTotalCorrect((Long) item[3]);
            saveGameInfoObj.setTotalIncorrect((Long) item[4]);
            saveGameInfoObj.setTotalQuizItems((Long) item[5]);
            saveGameInfoObjs.add(saveGameInfoObj);
            }

        saveGameInfoInOutDto.setSavedGameDetails(saveGameInfoObjs);

        return saveGameInfoInOutDto;
    }

    @Override
    public SaveGameInfoInOutDto getAllNotCompletedQuizzes(SaveGameInfoInOutDto inOutDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        SaveGameInfoInOutDto outDto = new SaveGameInfoInOutDto();
        List<SaveGameInfoObj> saveGameInfoObjs = new ArrayList<>();

        List<Object[]> notCompletedQuizzes = saveGameInfoLogic.getAllNotCompletedQuizzes(inOutDto.getUserIdPk());

        for(Object[] saveGameInfo : notCompletedQuizzes){
            SaveGameInfoObj saveGameInfoObj = new SaveGameInfoObj();
            String encryptedIdPk = ExtendUtil.encryptedFunction((Integer) saveGameInfo[0]);
            saveGameInfoObj.setIdPk((Integer) saveGameInfo[0]);
            saveGameInfoObj.setEncryptedIdPk(encryptedIdPk);
            saveGameInfoObj.setDate((String) saveGameInfo[1]);
            saveGameInfoObj.setQuizUUID((String) saveGameInfo[2]);
            saveGameInfoObj.setIncompleteQuizzes((Long) saveGameInfo[3]);
            saveGameInfoObjs.add(saveGameInfoObj);
        }

        outDto.setSavedGameDetails(saveGameInfoObjs);

        return outDto;
    }




}
