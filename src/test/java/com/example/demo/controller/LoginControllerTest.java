package com.example.demo.controller;

import com.example.demo.common.CommonConstant;
import com.example.demo.model.dto.RegistrationInOutDto;
import com.example.demo.model.dto.SavingDto;
import com.example.demo.model.service.AuthenticationService;
import com.example.demo.model.service.UserInformationService;
import com.example.demo.response.ResponseMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    private MockMvc mockMvc;

    private AutoCloseable autoCloseable;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserInformationService userInformationService;

    @Mock
    private AuthenticationService authenticationService;


    @BeforeEach
    public void openMocks() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @AfterEach
    public void releaseMocks() throws  Exception {
        autoCloseable.close();
    }

    @Test
    public void loginControllerTest1() throws IOException {

    }
}
