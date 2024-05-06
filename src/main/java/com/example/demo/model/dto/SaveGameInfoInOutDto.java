package com.example.demo.model.dto;

import com.example.demo.obj.SaveGameInfoObj;
import com.example.demo.obj.UserInformationObj;
import lombok.Data;

import java.util.List;

@Data
public class SaveGameInfoInOutDto {
    private UserInformationObj userInformationObj;

    private String quizUUID;

    private boolean updateSaveGameInfo;

    private int idPk;

    private int userIdPk;

    private List<SaveGameInfoObj> savedGameDetails;

    private String date;
}
