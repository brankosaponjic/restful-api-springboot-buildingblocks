package com.brankosaponjic.restfulapispringbootbuildingblocks.services;

import com.brankosaponjic.restfulapispringbootbuildingblocks.entities.User;
import com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions.UserNotFoundException;
import com.brankosaponjic.restfulapispringbootbuildingblocks.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found in repository.");
        }
        return user;
    }

    public User updateUserById(User user, Long id) throws UserNotFoundException {

        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in repository. Provide the correct user id.");
        }
        userRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
