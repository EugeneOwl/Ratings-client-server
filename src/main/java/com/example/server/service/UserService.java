package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    String MOBILE_NUMBER_PATTERN = "^((375)([0-9]{9}))$";
    String NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN = "[^0-9]";
    int USER_COUNT_PER_PAGE = 5;

    /**
     * @param id user id
     * @return userDto
     * @throws javax.persistence.EntityNotFoundException in case no user with the id exists in database.
     */
    UserDto getUserById(Long id);

    /**
     * Updates user by the id. Sets new mobile number and roles according userUpdateDto and saves it.
     *
     * @param userUpdateDto with mobile number and role ids from client.
     * @throws javax.persistence.EntityNotFoundException in case no user with the id exists in database.
     */
    void updateUser(UserUpdateDto userUpdateDto);

    List<UserDto> getAllUsers();

    String cleanUpMobileNumber(final String mobileNumber);

    Page<UserDto> getPageOfUsers(int page,
                                 String sortByColumn,
                                 String filterPattern);
}
