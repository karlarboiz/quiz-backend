package com.example.demo.model.service.impl;

import com.example.demo.obj.PasswordResetObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class ForgotPasswordServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendForgotPasswordEmail(PasswordResetObj passwordResetObj) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("demomailtrap.com");
            message.setTo(passwordResetObj.getEmail());
            message.setSubject("This is a reset Password");
            String htmlContent = "<h1>  </h1>";
            emailSender.send(message);
        } catch (MailSendException | MailAuthenticationException e) {
            System.out.println(e.getMessage());
        }

    }
}
