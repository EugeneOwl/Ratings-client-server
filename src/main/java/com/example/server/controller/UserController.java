package com.example.server.controller;

import com.example.server.dto.UserDto;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "server/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") final Long id) {

        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto updateUser(@RequestBody final UserDto userDto) {
        userService.addOrUpdateUserIfValid(userDto);

        return userDto;
    }
}
