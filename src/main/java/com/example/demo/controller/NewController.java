package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/something")
public class NewController {

    @GetMapping("/kwan")
    public ResponseEntity<String> getMeSomething(){

        return new ResponseEntity<>("hello",HttpStatus.FOUND);
    }

}
