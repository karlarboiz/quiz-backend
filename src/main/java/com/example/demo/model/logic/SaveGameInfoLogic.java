package com.example.demo.model.logic;

import com.example.demo.model.dao.entity.SaveGameInfo;

import java.util.List;

public interface SaveGameInfoLogic {

    public void saveGameInfoLogic(SaveGameInfo saveGameInfo);

    public SaveGameInfo findLatestSaveGameInfo();

    public SaveGameInfo updateSaveGameInfoIdPk(int idPk);

    public List<Object[]> getAllCompletedActiveSaveGameInfoOfAUser(int userIdPk);
}
