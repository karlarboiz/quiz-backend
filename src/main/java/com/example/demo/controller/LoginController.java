package com.example.demo.controller;

import com.example.demo.common.CommonConstant;
import com.example.demo.model.dto.ErrorBody;
import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.model.service.impl.ForgotPasswordServiceImpl;
import com.example.demo.obj.PasswordResetObj;
import com.example.demo.obj.UserInformationObj;
import com.example.demo.request.RequestAuthentication;
import com.example.demo.request.RequestPasswordReset;
import com.example.demo.response.ForgotPasswordResponse;
import com.example.demo.response.ResponseAuthentication;
import com.example.demo.response.ResponseMessage;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/quiz/api")
public class LoginController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ForgotPasswordServiceImpl forgotPasswordService;

    @PostMapping("/register")
    public ResponseEntity<ResponseAuthentication>
    register (
            @RequestPart(value = "image",required = false) MultipartFile file,
            @RequestPart(value = "userinfo") RegistrationInOutDto userInfo) {

        RegistrationInOutDto registrationInOutDto = userInformationService.validateUserRegistration(userInfo);

        ResponseAuthentication responseAuthentication = new ResponseAuthentication();


        try{
            if(!registrationInOutDto.isValid()){
                responseAuthentication.setValid(false);
                responseAuthentication.setErrorlist(registrationInOutDto.getErrorlist());
                responseAuthentication.setResponseAuthErrors(registrationInOutDto.getResponseRegErrors());
            }else {
                //set image
                userInfo.setImage(file);
                // Assuming saveUserInformation() method performs the save operation without returning anything
                SavingDto savingDto = userInformationService.saveUserInformation(userInfo);

                if(savingDto.getSaveResult().matches(CommonConstant.RETURN_CD)) {
                    responseAuthentication.setValid(true);
                    responseAuthentication.setMessage("Successfully registered");
                }else {
                    responseAuthentication.setValid(false);
                    responseAuthentication.setMessage(savingDto.getError());

                }
            }
        }catch (NullPointerException | IllegalArgumentException | MaxUploadSizeExceededException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseAuthentication);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthentication>
    getCredentials(@RequestBody RequestAuthentication requestAuthentication)
            throws SQLException {
        //get validation results
        ResponseAuthentication responseAuthentication = authenticationService.validateResponseAuthentication(requestAuthentication);

        if(!responseAuthentication.getResponseAuthErrors().isEmpty()){

            return ResponseEntity.ok(responseAuthentication);
        }else {
            ResponseAuthentication responseAuthentication1 = authenticationService.responseAuthentication(requestAuthentication);

            return ResponseEntity.ok(responseAuthentication1);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPasswordHandler(@RequestBody RequestPasswordReset requestPasswordReset){
        PasswordResetObj passwordResetObj = new PasswordResetObj();


        passwordResetObj.setEmail(requestPasswordReset.getEmail());

        forgotPasswordService.sendForgotPasswordEmail(passwordResetObj);

        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();

        return ResponseEntity.ok(forgotPasswordResponse);
    }

}
