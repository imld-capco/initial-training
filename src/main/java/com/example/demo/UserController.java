package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOGGER.info("Requesting all users");
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") String id) {
        Optional<User> optionalUser = userRepo.findById(id);

        LOGGER.info(String.format("Checking if user with Id: %s exists", id));

        if (optionalUser.isPresent()) {
            LOGGER.info(String.format("User with Id: %s exists", id));
            return optionalUser.get();
        }

        LOGGER.error(String.format("User with Id: %s DOES NOT EXIST", id));

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        LOGGER.info(String.format("Adding user %s %s", user.firstName, user.lastName));
        return userRepo.insert(user);
    }
}
