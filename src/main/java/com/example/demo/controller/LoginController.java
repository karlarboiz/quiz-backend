package com.example.demo.controller;

import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.obj.UserInformationObj;
import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;
import com.example.demo.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/quiz/api")
public class LoginController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<Void>> handle(@RequestBody RegistrationInOutDto userInfo){
        // Assuming saveUserInformation() method performs the save operation without returning anything
        userInformationService.saveUserInformation(userInfo);

        ResponseMessage<Void> responseMessage = new ResponseMessage<>();

        responseMessage.setSuccess(true);
        responseMessage.setMessage("It worked!");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthentication> loginMethod(@RequestBody RequestAuthentication requestAuthentication) throws SQLException {

        return ResponseEntity.ok(authenticationService.responseAuthentication(requestAuthentication));
    }




}
