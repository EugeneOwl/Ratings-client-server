package com.example.server.controller;

import com.example.server.dto.AuthUserDto;
import com.example.server.dto.LoginRequestDto;
import com.example.server.dto.LoginResponseDto;
import com.example.server.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@RestController
@RequestMapping(value = "/server/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public LoginResponseDto login(@RequestBody final LoginRequestDto loginRequestDto) {

        return authenticationService.login(loginRequestDto);
    }

    @GetMapping(value = "/me")
    public AuthUserDto me() {

        return authenticationService.getMe();
    }
}
