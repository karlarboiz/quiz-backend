package com.example.demo.model.service;

import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;

import java.sql.SQLException;

public interface AuthenticationService {

    public ResponseAuthentication validateResponseAuthentication(RequestAuthentication requestAuthentication) throws SQLException;

    public ResponseAuthentication responseAuthentication(RequestAuthentication requestAuthentication) throws SQLException;
}
