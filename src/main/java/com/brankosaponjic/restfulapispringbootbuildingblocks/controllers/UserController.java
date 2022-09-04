package com.brankosaponjic.restfulapispringbootbuildingblocks.controllers;

import com.brankosaponjic.restfulapispringbootbuildingblocks.entities.User;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserAlreadyExistsException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserNameNotFoundException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserNotFoundException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
            userService.createUser(user);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
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
    public User findUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UserNameNotFoundException("Username: '" + username + "' not found in User Repository.");
        }
        return user;
    }
}
