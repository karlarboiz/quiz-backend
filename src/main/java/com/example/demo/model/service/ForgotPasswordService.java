package com.example.demo.model.service;

import com.example.demo.obj.PasswordResetObj;

public interface ForgotPasswordService {
    public void sendForgotPasswordEmail(PasswordResetObj passwordResetObj);
}
