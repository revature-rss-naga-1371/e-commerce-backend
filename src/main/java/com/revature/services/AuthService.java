package com.revature.services;

import com.revature.dtos.UserDto;
import com.revature.models.User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public User register(User user) {
        return userService.save(user);
    }

    public void resetPW(UserDto userDto) {
        System.out.println("USERDTO" + userDto);
        Optional<User> newUser = userService.findByCredentials(userDto.getEmail(), userDto.getCurrPW());
        if (newUser.isPresent()) {
            if (userDto.getNewPW().equals(userDto.getReNewPW())) {
                newUser.get().setPassword(userDto.getNewPW());
                userService.save(newUser.get());
            }
        }
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }
}
