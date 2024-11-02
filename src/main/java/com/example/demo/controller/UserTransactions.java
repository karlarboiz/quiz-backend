package com.example.demo.controller;


import com.example.demo.response.UserList;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTransactions {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseEntity<UserList> getListUsersUpdate() throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok(new UserList());
    }
}
