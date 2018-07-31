package com.example.server.service;

import com.example.server.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(int id);

    void addUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void removeUser(int id);

    List<UserDto> getAllUsers();

    boolean isUserValid(UserDto userDto);

    boolean addOrUpdateUserIfValid(UserDto userDto);
}
