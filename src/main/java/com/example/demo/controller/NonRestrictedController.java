package com.example.demo.controller;

import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.obj.UserInformationObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/quiz/api/info")
public class NonRestrictedController {

    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/users/record")
    public ResponseEntity<List<UserInformationObj>> usersRecord(){
        UserInformationInOutDto inDto = new UserInformationInOutDto();
        UserInformationInOutDto outDto = userInformationService.getUserAndTheirCompletedGames(inDto);
        List<UserInformationObj> userInformationObjList =  outDto.getStudentObjList();
        return new ResponseEntity<>(userInformationObjList, HttpStatus.OK);
    }
}
