package com.example.demo.model.service.impl;


import com.example.demo.common.CommonConstant;
import com.example.demo.model.dao.entity.Role;
import com.example.demo.model.dao.entity.UserInformation;
import com.example.demo.model.dao.entity.UserInformationAccount;
import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.logic.SaveGameInfoLogic;
import com.example.demo.model.logic.UserInformationAccountLogic;
import com.example.demo.model.logic.UserInformationLogic;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.obj.RegisterInputsObj;
import com.example.demo.obj.UserInformationObj;

import com.example.demo.util.ExtendUtil;
import com.example.demo.util.SeparatorUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserInformationLogic userInformationLogic;

    @Autowired
    private UserInformationAccountLogic userInformationAccountLogic;

    @Autowired
    private SaveGameInfoLogic saveGameInfoLogic;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    private final Validator validator;

    // Injecting the LocalValidatorFactoryBean using the @Qualifier annotation

    public UserInformationServiceImpl(@Qualifier("localValidatorFactoryBean") Validator validator) {

        this.validator = validator;

    }


    @Override
    public RegistrationInOutDto validateUserRegistration(RegistrationInOutDto registrationInOutDto) {
        System.out.println(env.getProperty("file.upload-dir"));
        RegistrationInOutDto registrationValidation = new RegistrationInOutDto();

        RegisterInputsObj registerInputsObj = new RegisterInputsObj();

        registerInputsObj.setFirstName(registrationInOutDto.getFirstName());

        registerInputsObj.setLastName(registrationInOutDto.getLastName());

        registerInputsObj.setEmail(registrationInOutDto.getEmail());

        registerInputsObj.setUsername(registrationInOutDto.getUsername());

        registerInputsObj.setPassword(registrationInOutDto.getPassword());

        Set<ConstraintViolation<RegisterInputsObj>> violations = validator.validate(registerInputsObj);

        List<String> firstNameErrors = new ArrayList<>();
        List<String> lastNameErrors = new ArrayList<>();
        List<String> emailAddressErrors = new ArrayList<>();
        List<String> usernameErrors = new ArrayList<>();
        List<String> passwordErrors = new ArrayList<>();


        try{
            for(ConstraintViolation item: violations) {
                if(item.getPropertyPath().toString().matches("username")){
                    usernameErrors.add(item.getMessage());
                }else if(item.getPropertyPath().toString().matches("email")){
                    emailAddressErrors.add(item.getMessage());
                } else if (item.getPropertyPath().toString().matches("password")) {
                    passwordErrors.add(item.getMessage());
                }else if (item.getPropertyPath().toString().matches("firstName")){
                    firstNameErrors.add(item.getMessage());
                } else {
                    lastNameErrors.add(item.getMessage());

                }

            }
        }catch (ConstraintViolationException e){
            System.out.println(e.getMessage());

        }
        Map<String,List<String>> errorList = new HashMap<>();
        errorList.put("firstName",firstNameErrors);
        errorList.put("lastName",lastNameErrors);
        errorList.put("username",usernameErrors);
        errorList.put("email",emailAddressErrors);
        errorList.put("password",passwordErrors);

        registrationValidation.setValid(violations.isEmpty());

        registrationValidation.setErrorlist(errorList);
        return  registrationValidation;
    }

    @Override
    public SavingDto saveUserInformation(RegistrationInOutDto userInformationEntity)
            throws IOException {
        SavingDto savingDto = new SavingDto();
        UserInformation userInformation = new UserInformation();
        UserInformationAccount userInformationAccount = new UserInformationAccount();
        Timestamp date = new Timestamp(System.currentTimeMillis());

        if(!userInformationEntity.isUpdate()) {
            userInformation.setRegDate(date);
            userInformation.setUsername(userInformationEntity.getUsername());
            userInformation.setEmail(userInformationEntity.getEmail());
            userInformation.setRole(Role.USER);
            userInformation.setPassword(passwordEncoder.encode(userInformationEntity.getPassword()));
            userInformation.setUpdateId(userInformationEntity.getUsername());
            userInformation.setRegId(userInformationEntity.getUsername());
            userInformation.setUpdateDate(date);
            userInformation.setDeleteFlg(false);
            userInformationLogic.saveUserInformation(userInformation);

            Path uploadPath = Paths.get(env.getProperty("file.upload-dir"));
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = userInformationEntity.getUsername() +"-profile"+ ".jpg";
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(userInformationEntity.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String picture = env.getProperty("file.upload-dir") + SeparatorUtil.separateDirectorSign()
                    +fileName;
            userInformationAccount.setUserIdPk(userInformation.getIdPk());
            userInformationAccount.setFirstName(userInformationEntity.getFirstName());
            userInformationAccount.setLastName(userInformationEntity.getLastName());
            userInformationAccount.setDisplayPicture(picture);
            userInformationAccount.setRegDate(date);
            userInformationAccount.setRegId(userInformationEntity.getUsername());
            userInformationAccount.setUpdateId(userInformationEntity.getUsername());
            userInformationAccount.setUpdateDate(date);
            userInformationAccount.setDeleteFlg(false);
            userInformationAccountLogic.saveUserInformationAccount(userInformationAccount);
        }else {
            userInformation.setUsername(userInformationEntity.getUsername());
            userInformation.setEmail(userInformationEntity.getEmail());

            userInformation.setPassword(passwordEncoder.encode(userInformationEntity.getPassword()));
            userInformation.setUpdateId(userInformationEntity.getUsername());
            userInformation.setRegId(userInformationEntity.getUsername());
            userInformation.setUpdateDate(date);
            userInformation.setDeleteFlg(false);
            userInformationLogic.saveUserInformation(userInformation);

            userInformationAccount.setUserIdPk(userInformation.getIdPk());
            userInformationAccount.setFirstName(userInformationEntity.getFirstName());
            userInformationAccount.setLastName(userInformationEntity.getLastName());
            userInformationAccount.setDisplayPicture("thisisdisplaypicture");
            userInformationAccount.setRegDate(date);
            userInformationAccount.setRegId(userInformationEntity.getUsername());
            userInformationAccount.setUpdateId(userInformationEntity.getUsername());
            userInformationAccount.setUpdateDate(date);
            userInformationAccount.setDeleteFlg(false);
            userInformationAccountLogic.saveUserInformationAccount(userInformationAccount);
        }
        savingDto.setSaveResult(CommonConstant.RETURN_CD);
        return savingDto;
    }

    @Override
    public UserInformationInOutDto getUserDetailsUsingUsername(String username) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        UserInformationInOutDto userInformationInOutDto = new UserInformationInOutDto();
        UserInformationObj userInformationObj = new UserInformationObj();
        List<Object[]> userInformation = userInformationLogic.matchedLoginCredentialsUsingUsername(username);

        if(userInformation.isEmpty()) {
            return userInformationInOutDto;
        }

        userInformationObj.setEncryptedIdPk(ExtendUtil.encryptedFunction((Integer) userInformation.get(0)[0]));
        userInformationObj.setUsername((String) userInformation.get(0)[1]);
        userInformationObj.setEmail((String) userInformation.get(0)[2]);
        userInformationObj.setFirstName((String) userInformation.get(0)[3]);
        userInformationObj.setLastName((String) userInformation.get(0)[4]);
        userInformationObj.setDisplayPicture((String) userInformation.get(0)[5]);
        userInformationInOutDto.setUserInformationObj(userInformationObj);

        return userInformationInOutDto;
    }

    @Override
    public UserInformationInOutDto getUserAndTheirCompletedGames(UserInformationInOutDto inDto) {
        UserInformationInOutDto outDto = new UserInformationInOutDto();

        List<UserInformationObj> userInformationObjList = new ArrayList<UserInformationObj>();
        List<Object[]> completeQuizzesOfUsers = saveGameInfoLogic.getAllCompletedQuizzesPerUser();
        for(Object[] objects: completeQuizzesOfUsers) {
            UserInformationObj userInformationObj = new UserInformationObj();

            userInformationObj.setId((Integer) objects[0]);
            userInformationObj.setUsername((String) objects[1]);
            userInformationObj.setRegistrationDate((Timestamp) objects[2]);
            userInformationObj.setCompletedQuizzes((Long) objects[3]);
            userInformationObjList.add(userInformationObj);

        }
        outDto.setStudentObjList(userInformationObjList);

        return outDto;

    }


}

