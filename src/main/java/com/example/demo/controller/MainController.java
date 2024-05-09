package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.model.service.*;
import com.example.demo.obj.ItemDataQuizObj;
import com.example.demo.obj.SaveGameInfoObj;
import com.example.demo.obj.UserInformationObj;
import com.example.demo.request.RequestListOfQuizData;
import com.example.demo.response.ResponseMessage;
import com.example.demo.response.ResponseProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/main/user")
public class MainController {
    @Autowired
    private LoggedInUserService loggedInUserService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SaveGameInfoService saveGameInfoService;

    @Autowired
    private ItemDataQuizService itemDataQuizService;

    @Autowired
    private RecordScoreService recordScoreService;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private HttpServletRequest request;
    @PostMapping("/start")
    public ResponseEntity<ResponseMessage<Void>>
    saveGameInfo(@RequestBody List<RequestListOfQuizData> requestListOfQuizData) {

        ResponseMessage<Void> responseMessage = new ResponseMessage<>();
        try {
            UUID quizUUID = UUID.randomUUID();
            UserInformationObj userInformationObj = new UserInformationObj();

            SaveGameInfoInOutDto intDto = new SaveGameInfoInOutDto();

            userInformationObj.setId(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());

            intDto.setUserInformationObj(userInformationObj);
            intDto.setQuizUUID(quizUUID.toString());
            intDto.setUpdateSaveGameInfo(false);

            saveGameInfoService.saveGameInformation(intDto);

            SaveGameInfoInOutDto saveGameInfoInOutDto = saveGameInfoService.findLatestSaveGameInfo();
            List<ItemDataQuizInOutDto> itemDataQuizInOutDtoList = new ArrayList<>();
            int i = 0;
            for (RequestListOfQuizData item : requestListOfQuizData) {

                i++;
                ItemDataQuizInOutDto inOutDto = new ItemDataQuizInOutDto();

                inOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());

                inOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());

                inOutDto.setQuizIdTag(item.getId());

                inOutDto.setIncrementId(i);
                inOutDto.setCorrectAnswer(item.getCorrectAnswer());
                itemDataQuizInOutDtoList.add(inOutDto);
            }

            itemDataQuizService.saveListOfItemDataQuiz(itemDataQuizInOutDtoList);

            responseMessage.setMessage("Game is in Session");
            responseMessage.setSuccess(true);
        }catch (Exception e){
            responseMessage.setMessage("Something went wrong");
            responseMessage.setSuccess(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/quiz-items")
    public ResponseEntity<List<ItemDataQuizObj>> getKarl(){

        SaveGameInfoInOutDto saveGameInfoInOutDto = saveGameInfoService.findLatestSaveGameInfo();

        ItemDataQuizInOutDto inDto = new ItemDataQuizInOutDto();

        inDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());

        inDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());

        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfQuizItems(inDto);

        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();

        return ResponseEntity.ok(itemDataQuizObjList);
    }
    @PutMapping("/game")
    public ResponseEntity<ResponseMessage<Void>> saveGameInfo(@RequestBody ItemInOutDto itemInOutDto) {

        SaveGameInfoInOutDto saveGameInfoInOutDto  = saveGameInfoService.findLatestSaveGameInfo();

        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();

        itemDataQuizInOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
        itemDataQuizInOutDto.setQuizIdTag(itemInOutDto.getQuizIdTag());
        itemDataQuizInOutDto.setUserAnswer(itemInOutDto.getUserAnswer());
        itemDataQuizService.saveItemDataQuiz(itemDataQuizInOutDto);
        recordScoreService.saveRecordedScore(itemDataQuizInOutDto);

        ItemDataQuizInOutDto itemDataQuizInOutDto1 = itemDataQuizService.getListOfAnsweredQuizItems(itemDataQuizInOutDto);
        ItemDataQuizInOutDto itemDataQuizInOutDto2 = itemDataQuizService.getListOfQuizItems(itemDataQuizInOutDto);
        List<ItemDataQuizObj> answeredItemDataQuizObjList = itemDataQuizInOutDto1.getItemDataQuizObjList();
        List<ItemDataQuizObj> unansweredItemDataQuizObjList = itemDataQuizInOutDto2.getItemDataQuizObjList();

        if(answeredItemDataQuizObjList.size() == unansweredItemDataQuizObjList.size()) {

            saveGameInfoInOutDto.setUpdateSaveGameInfo(true);
            saveGameInfoService.updateSaveGameInfo(saveGameInfoInOutDto);
        }
        ResponseMessage<Void> responseMessage = new ResponseMessage<>();

        responseMessage.setMessage("Item Answered");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/game/session")
    public ResponseEntity<GameCheckObj> checkGameHandler(){
        HttpSession session = request.getSession(true);
        GameCheckObj gameCheckObj = new GameCheckObj();

        boolean gameOn = (boolean) session.getAttribute("gameOn");

        if(gameOn) {
            gameCheckObj.setGameOn(gameOn);
            gameCheckObj.setMessage("Game is currently on");
        }else {
            gameCheckObj.setGameOn(gameOn);
            gameCheckObj.setMessage("Game is currently off");
        }

        return ResponseEntity.ok(gameCheckObj);
    }
    @GetMapping("/fetch/game-result")
    public ResponseEntity<List<ItemDataQuizObj>> fetchGameResult(){
        SaveGameInfoInOutDto saveGameInfoInOutDto  = saveGameInfoService.findLatestSaveGameInfo();

        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();

        itemDataQuizInOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());

        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfAnswerQuizItemsBasedOnUserIdPkAndItemIdPk(itemDataQuizInOutDto);
        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();

        return ResponseEntity.ok(itemDataQuizObjList);
    }
    @GetMapping("/profile")
    public ResponseEntity<ResponseProfile> getProfile() throws Exception {
        ResponseProfile responseProfile = new ResponseProfile();
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            responseProfile.setAuth(true);
            responseProfile.setUsername(username);
            responseProfile.setMessage("Welcome Back, " + username);
        }catch (Exception e){
            throw new Exception("User Cannot be Found");
        }
        return ResponseEntity.ok(responseProfile);
    }

    @GetMapping("/game-history")
    public ResponseEntity<List<SaveGameInfoObj>> getHistory() {
        int userIdPk = loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId();

        SaveGameInfoInOutDto inDto = new SaveGameInfoInOutDto();

        inDto.setUserIdPk(userIdPk);

        SaveGameInfoInOutDto saveGameInfoInOutDto =saveGameInfoService.getAllDetailsOfSavedGames(inDto);
        List<SaveGameInfoObj> saveGameInfoObjs =  saveGameInfoInOutDto.getSavedGameDetails();
        return ResponseEntity.ok(saveGameInfoObjs);
    }

    @GetMapping("/game-history/{quizUUID}")
    public ResponseEntity<String> getSpecificQuizHistoryDetails(){
                
        return ResponseEntity.ok("hello");
    }


}
