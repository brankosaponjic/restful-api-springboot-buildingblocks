package com.brankosaponjic.restfulapispringbootbuildingblocks.controllers;

import com.brankosaponjic.restfulapispringbootbuildingblocks.entities.User;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserAlreadyExistsException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserNotFoundException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        try {
            return userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable("id") Long id) {
        try {
            return userService.updateUserById(user,id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }
}
