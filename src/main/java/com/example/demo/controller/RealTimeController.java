package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/real-time/data")
public class RealTimeController {

    @MessageMapping("/result")
    @SendTo("/greetings")
    public ResponseEntity<String> greeting(String something) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
