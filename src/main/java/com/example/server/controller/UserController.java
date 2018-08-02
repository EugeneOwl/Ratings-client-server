package com.example.server.controller;

import com.example.server.dto.UserDto;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "server/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto getById(@PathVariable("id") final int id) {

        return userService.getUserById(id);
    }

    @PostMapping
    public void updateUser(@RequestBody final UserDto userDto) {
        if (Objects.isNull(userDto)) {

            return;
        }

        this.userService.addOrUpdateUserIfValid(userDto);
    }
}
