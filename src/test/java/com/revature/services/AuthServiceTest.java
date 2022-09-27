package com.revature.services;

import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserService userServiceMock;
    private AuthService authService;

    @BeforeEach
    public void setUp(){
        userServiceMock = mock(UserService.class);
        when(userServiceMock.findByCredentials("test@email.com", "password"))
                .thenReturn(Optional.of(new User(1,"test@email.com", "password", "test", "user")));
        when(userServiceMock.save(new User(1,"test@email.com", "password", "test", "user")))
                .thenReturn( new User(1,"test@email.com", "password", "test", "user"));
        when(userServiceMock.getUsers())
                .thenReturn(List.of(new User(1,"test@email.com", "password", "test", "user")));
        authService = new AuthService(userServiceMock);
    }

    @Test
    public void findBYCredentials(){
        Optional<User> user ;
        user = authService.findByCredentials("test@email.com", "password");
        assertThat(user.get(), equalTo(new User(1,"test@email.com", "password", "test", "user")));
    }

    @Test
    public void saveTest(){
        User user;
        user = authService.register(new User(1,"test@email.com", "password", "test", "user"));
        assertThat(user, equalTo(new User(1,"test@email.com", "password", "test", "user")));
    }
    @Test
    public void getUsersTest(){
        List<User> users;
        users = authService.getUsers();
        assertThat(users, hasItem(new User(1,"test@email.com", "password", "test", "user")));
    }
}
