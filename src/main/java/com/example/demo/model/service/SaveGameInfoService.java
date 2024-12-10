package com.example.demo.model.service;

import com.example.demo.model.dao.entity.SaveGameInfo;
import com.example.demo.model.dto.SaveGameInfoInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface SaveGameInfoService {
    public SaveGameInfoInOutDto saveGameInformation(SaveGameInfoInOutDto inDto);

    public SaveGameInfoInOutDto findLatestSaveGameInfo();


    public SaveGameInfoInOutDto findSaveGameInfoBasedOnIdPk(SaveGameInfoInOutDto inDto);

    public void updateSaveGameInfo(SaveGameInfoInOutDto inOutDto);

    public SaveGameInfoInOutDto getAllDetailsOfSavedGames(SaveGameInfoInOutDto inDto);

    public SaveGameInfoInOutDto getAllNotCompletedQuizzes(SaveGameInfoInOutDto inOutDto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
}
