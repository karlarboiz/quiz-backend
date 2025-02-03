package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.model.service.*;
import com.example.demo.obj.GameCheckObj;
import com.example.demo.obj.ItemDataQuizObj;
import com.example.demo.obj.SaveGameInfoObj;
import com.example.demo.obj.UserInformationObj;
import com.example.demo.request.RequestListOfQuizData;
import com.example.demo.response.ResponseMessage;
import com.example.demo.response.ResponseProfile;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
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

//    @PostMapping("/start")
//    public ResponseEntity<ResponseMessage>
//    saveGameInfo(@RequestBody List<RequestListOfQuizData> requestListOfQuizData) {
//
//        ResponseMessage responseMessage = new ResponseMessage();
//        try {
//            UUID quizUUID = UUID.randomUUID();
//            UserInformationObj userInformationObj = new UserInformationObj();
//
//            SaveGameInfoInOutDto intDto = new SaveGameInfoInOutDto();
//
//            userInformationObj.setId(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//
//            intDto.setUserInformationObj(userInformationObj);
//            intDto.setQuizUUID(quizUUID.toString());
//            intDto.setUpdateSaveGameInfo(false);
//
//            saveGameInfoService.saveGameInformation(intDto);
//
//            SaveGameInfoInOutDto saveGameInfoInOutDto = saveGameInfoService.findLatestSaveGameInfo();
//            List<ItemDataQuizInOutDto> itemDataQuizInOutDtoList = new ArrayList<>();
//            int i = 0;
//            for (RequestListOfQuizData item : requestListOfQuizData) {
//
//                i++;
//                ItemDataQuizInOutDto inOutDto = new ItemDataQuizInOutDto();
//
//                inOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//
//                inOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
//
//                inOutDto.setQuizIdTag(item.getId());
//
//                inOutDto.setIncrementId(i);
//                inOutDto.setCorrectAnswer(item.getCorrectAnswer());
//                itemDataQuizInOutDtoList.add(inOutDto);
//            }
//
//            itemDataQuizService.saveListOfItemDataQuiz(itemDataQuizInOutDtoList);
//
//            responseMessage.setMessage("Game is in Session");
//            responseMessage.setSuccess(true);
//        }catch (Exception e){
//            responseMessage.setMessage("Something went wrong");
//            responseMessage.setSuccess(false);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
//    }

//    @GetMapping("/quiz-items")
//    public ResponseEntity<List<ItemDataQuizObj>> getKarl(){
//
//        SaveGameInfoInOutDto saveGameInfoInOutDto = saveGameInfoService.findLatestSaveGameInfo();
//
//        ItemDataQuizInOutDto inDto = new ItemDataQuizInOutDto();
//
//        inDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
//
//        inDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//
//        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfQuizItems(inDto);
//
//        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();
//
//        return ResponseEntity.ok(itemDataQuizObjList);
//    }
//    @PutMapping("/game")
//    public ResponseEntity<ResponseMessage> saveGameInfo(@RequestBody ItemInOutDto itemInOutDto) {
//
//        SaveGameInfoInOutDto saveGameInfoInOutDto  = saveGameInfoService.findLatestSaveGameInfo();
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();
//
//        itemDataQuizInOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
//        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//        itemDataQuizInOutDto.setQuizIdTag(itemInOutDto.getQuizIdTag());
//        itemDataQuizInOutDto.setUserAnswer(itemInOutDto.getUserAnswer());
//        itemDataQuizService.saveItemDataQuiz(itemDataQuizInOutDto);
//        recordScoreService.saveRecordedScore(itemDataQuizInOutDto);
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto1 = itemDataQuizService.getListOfAnsweredQuizItems(itemDataQuizInOutDto);
//        ItemDataQuizInOutDto itemDataQuizInOutDto2 = itemDataQuizService.getListOfQuizItems(itemDataQuizInOutDto);
//        List<ItemDataQuizObj> answeredItemDataQuizObjList = itemDataQuizInOutDto1.getItemDataQuizObjList();
//        List<ItemDataQuizObj> unansweredItemDataQuizObjList = itemDataQuizInOutDto2.getItemDataQuizObjList();
//
//        if(answeredItemDataQuizObjList.size() == unansweredItemDataQuizObjList.size()) {
//
//            saveGameInfoInOutDto.setUpdateSaveGameInfo(true);
//            saveGameInfoService.updateSaveGameInfo(saveGameInfoInOutDto);
//        }
//        ResponseMessage responseMessage = new ResponseMessage();
//        responseMessage.setSuccess(true);
//        responseMessage.setMessage("Item Answered");
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
//    }

//    @PutMapping("/game-resume/{id}")
//    public ResponseEntity<ResponseMessage>
//    saveGameInfoResume(@RequestBody ItemInOutDto itemInOutDto,@PathVariable int id) {
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();
//
//        itemDataQuizInOutDto.setSaveQuizInfoIdPk(id);
//        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//        itemDataQuizInOutDto.setQuizIdTag(itemInOutDto.getQuizIdTag());
//        itemDataQuizInOutDto.setUserAnswer(itemInOutDto.getUserAnswer());
//        itemDataQuizService.saveItemDataQuiz(itemDataQuizInOutDto);
//        recordScoreService.saveRecordedScore(itemDataQuizInOutDto);
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto1 = itemDataQuizService.getListOfAnsweredQuizItems(itemDataQuizInOutDto);
//        ItemDataQuizInOutDto itemDataQuizInOutDto2 = itemDataQuizService.getListOfQuizItems(itemDataQuizInOutDto);
//        List<ItemDataQuizObj> answeredItemDataQuizObjList = itemDataQuizInOutDto1.getItemDataQuizObjList();
//        List<ItemDataQuizObj> unansweredItemDataQuizObjList = itemDataQuizInOutDto2.getItemDataQuizObjList();
//
//        if(answeredItemDataQuizObjList.size() == unansweredItemDataQuizObjList.size()) {
//            SaveGameInfoInOutDto saveGameInfoInDto = new SaveGameInfoInOutDto();
//
//            saveGameInfoInDto.setIdPk(id);
//            SaveGameInfoInOutDto saveGameInfoInOutDto  = saveGameInfoService.findSaveGameInfoBasedOnIdPk(saveGameInfoInDto);
//            saveGameInfoInOutDto.setUpdateSaveGameInfo(true);
//            saveGameInfoService.updateSaveGameInfo(saveGameInfoInOutDto);
//        }
//        ResponseMessage responseMessage = new ResponseMessage();
//        responseMessage.setSuccess(true);
//        responseMessage.setMessage("Item Answered");
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
//    }

//    @GetMapping("/fetch/game-result")
//    public ResponseEntity<List<ItemDataQuizObj>> fetchGameResult(){
//        SaveGameInfoInOutDto saveGameInfoInOutDto  = saveGameInfoService.findLatestSaveGameInfo();
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();
//
//        itemDataQuizInOutDto.setSaveQuizInfoIdPk(saveGameInfoInOutDto.getIdPk());
//        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//
//        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfAnswerQuizItemsBasedOnUserIdPkAndItemIdPk(itemDataQuizInOutDto);
//        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();
//
//        return ResponseEntity.ok(itemDataQuizObjList);
//    }

//    @GetMapping("/fetch/game-result/{id}")
//    public ResponseEntity<List<ItemDataQuizObj>> fetchGameResultBasedOnIdPk(@PathVariable int id){
//
//        ItemDataQuizInOutDto itemDataQuizInOutDto = new ItemDataQuizInOutDto();
//
//        itemDataQuizInOutDto.setSaveQuizInfoIdPk(id);
//        itemDataQuizInOutDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
//
//        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfAnswerQuizItemsBasedOnUserIdPkAndItemIdPk(itemDataQuizInOutDto);
//        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();
//
//
//
//        return ResponseEntity.ok(itemDataQuizObjList);
//    }


    @GetMapping("/profile")
    public ResponseEntity<ResponseProfile> getProfile() throws MalformedJwtException, SignatureException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ResponseProfile responseProfile = new ResponseProfile();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            responseProfile.setAuth(false);
            responseProfile.setMessage("User is not authenticated.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseProfile);
        }


        UserInformationInOutDto userInformationInOutDto1 = userInformationService.getUserDetailsUsingUsername(authentication.getName());

        UserInformationObj userInformationObj = userInformationInOutDto1.getUserInformationObj();
        String username = authentication.getName();
        responseProfile.setEncryptedIdPk(userInformationObj.getEncryptedIdPk());
        responseProfile.setAuth(true);
        responseProfile.setUsername(username);
        responseProfile.setMessage("Welcome Back, " + username);
        return ResponseEntity.ok(responseProfile);
    }

    @GetMapping("/profile-details")
    public ResponseEntity<UserInformationObj> getProfileDetais() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserInformationInOutDto userInformationInOutDto = new UserInformationInOutDto();

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        UserInformationInOutDto userInformationInOutDto1 = userInformationService.getUserDetailsUsingUsername(authentication.getName());

        UserInformationObj userInformationObj = userInformationInOutDto1.getUserInformationObj();

        return ResponseEntity.ok(userInformationObj);
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

    @GetMapping("/resume-quiz/{id}")
    public ResponseEntity<List<ItemDataQuizObj>> getResumeQuizUnansweredItems(@PathVariable int id){
        ItemDataQuizInOutDto inDto = new ItemDataQuizInOutDto();

        inDto.setSaveQuizInfoIdPk(id);

        inDto.setQuizUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());

        ItemDataQuizInOutDto outDto = itemDataQuizService.getListOfQuizItems(inDto);

        List<ItemDataQuizObj> itemDataQuizObjList = outDto.getItemDataQuizObjList();

        return ResponseEntity.ok(itemDataQuizObjList);
    }

    @GetMapping("/game-history/incomplete-quizzes")
    public ResponseEntity<List<SaveGameInfoObj>> getIncompleteQuizByUser() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        SaveGameInfoInOutDto inDto = new SaveGameInfoInOutDto();

        inDto.setUserIdPk(loggedInUserService.getLoggedInUserDetails().getUserInformationObj().getId());
        SaveGameInfoInOutDto outDto = saveGameInfoService.getAllNotCompletedQuizzes(inDto);

        List<SaveGameInfoObj> saveGameInfoObjList = outDto.getSavedGameDetails();

        return ResponseEntity.ok(saveGameInfoObjList);
    }



    @PutMapping("/profile-details/update")
    public ResponseEntity<ResponseMessage> updateProfileDetails(@RequestBody RegistrationInOutDto registrationInOutDto) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();

        userInformationService.saveUserInformation(registrationInOutDto);

        responseMessage.setMessage("Your Information has been updated!");
        responseMessage.setSuccess(true);

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }



}
