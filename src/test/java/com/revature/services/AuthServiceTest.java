package com.revature.services;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserService userServiceMock;
    private AuthService authService;

    @BeforeEach
    public void setUp(){
        userServiceMock = mock(UserService.class);
    }
}
