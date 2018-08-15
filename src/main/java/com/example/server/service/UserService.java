package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    String MOBILE_NUMBER_PATTERN = "^((375)([0-9]{9}))$";
    String NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN = "[^0-9]";

    UserDto getUserById(Long id);

    void updateUser(UserUpdateDto userUpdateDto);

    List<UserDto> getAllUsers();

    String cleanUpMobileNumber(final String mobileNumber);
}
