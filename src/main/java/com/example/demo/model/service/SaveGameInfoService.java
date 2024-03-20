package com.example.demo.model.service;

import com.example.demo.model.dto.SaveGameInfoInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;

public interface SaveGameInfoService {
    public SaveGameInfoInOutDto saveGameInformation(SaveGameInfoInOutDto inDto);

    public SaveGameInfoInOutDto findLatestSaveGameInfo();

    public void updateSaveGameInfo(SaveGameInfoInOutDto inOutDto);

    public SaveGameInfoInOutDto getAllDetailsOfSavedGames(SaveGameInfoInOutDto inDto);
}
