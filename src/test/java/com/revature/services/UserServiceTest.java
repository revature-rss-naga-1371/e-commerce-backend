package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepositoryMock;
    private UserService userService;

    @BeforeEach
    public void init() {
        userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByEmailAndPassword("testuser@gmail.com", "password"))
                .thenReturn(Optional.of(new User(1, "testuser@gmail.com", "password", "Test", "User")));
        when(userRepositoryMock.findAll())
                .thenReturn(List.of(new User(1, "testuser@gmail.com", "password", "Test", "User")));
        when(userRepositoryMock.save(new User(1, "bestuser@gmail.com", "pass", "Robert", "Nivon")))
                .thenReturn(new User(1, "bestuser@gmail.com", "pass", "Robert", "Nivon"));
        userService = new UserService(userRepositoryMock);
    }

    @Test
    public void findByCredentialsUserFoundTest() {
        Optional<User> user;
        assertThat(userService.findByCredentials("testuser@gmail.com", "password").get(), equalTo(new User(1, "testuser@gmail.com", "password", "Test", "User")));
    }

    @Test
    public void findByCredentialsUserNotFoundTest() {
        Optional<User> user;
        assertFalse(userService.findByCredentials("testuser@yahoo.com", "pass").isPresent());
    }

    @Test
    public void getUsersTest() {
        List<User> users = userService.getUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void saveTest() {
        User user = new User(2,"bestuser@gmail.com", "pass", "Robert", "Nivon");
        userService.save(user);
        verify(userRepositoryMock).save(user);
    }

}
