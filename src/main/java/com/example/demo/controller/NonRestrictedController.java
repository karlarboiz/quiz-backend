package com.example.demo.controller;

import com.example.demo.model.dto.UserInformationInOutDto;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.obj.UserInformationObj;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/quiz/api/info")
public class NonRestrictedController {

    @Autowired
    private UserInformationService userInformationService;

    //root path for image files
    private String FILE_PATH_ROOT = "D:/Projects/images/";


    @GetMapping("/users/record")
    public ResponseEntity<List<UserInformationObj>> usersRecord(){
        UserInformationInOutDto inDto = new UserInformationInOutDto();
        UserInformationInOutDto outDto = userInformationService.getUserAndTheirCompletedGames(inDto);
        List<UserInformationObj> userInformationObjList =  outDto.getStudentObjList();
        return new ResponseEntity<>(userInformationObjList, HttpStatus.OK);
    }


    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
