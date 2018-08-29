package com.example.server.controller;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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

    @GetMapping("/page")
    public Page<UserDto> getPageOfUsers(
            final Pageable pageable,
            @RequestParam(value = "filterPattern", defaultValue = "") final String filterPattern
    ) {
        return userService.getPageOfUsers(pageable, filterPattern);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public UserUpdateDto updateUser(@Valid @RequestBody final UserUpdateDto userUpdateDto) {
        userService.updateUser(userUpdateDto);

        return userUpdateDto;
    }
}
