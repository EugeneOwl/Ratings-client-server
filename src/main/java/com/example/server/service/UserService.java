package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    String MOBILE_NUMBER_PATTERN = "^((375)([0-9]{9}))$";
    String NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN = "[^0-9]";

    /**
     * Returns user Dto of user with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return user Dto of user with the given identifier.
     * @throws javax.persistence.EntityNotFoundException if no user exists for given id.
     */
    UserDto getUserById(Long id);

    /**
     * Updates existing user received from given userUpdate Dto.
     * Also cleans up mobile number of given userUpdateDto.
     *
     * @param userUpdateDto must have existing user identifier.
     * @throws javax.persistence.EntityNotFoundException if no user exists for given user Dto.
     * @throws javax.persistence.EntityNotFoundException if any user does not exist
     *                                                   from identifiers list of given userUpdate Dto.
     * @see UserService#cleanUpMobileNumber
     */
    void updateUser(UserUpdateDto userUpdateDto);

    /**
     * Returns user Dto list of all users from database sorted by identifier.
     *
     * @return user Dto list of all users from database sorted by identifier.
     */
    List<UserDto> getAllUsers();

    /**
     * Returns mobile number without any not valid literals.
     *
     * @param mobileNumber
     * @return mobile number without any not valid literals.
     * @see UserService#NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN
     */
    String cleanUpMobileNumber(final String mobileNumber);

    /**
     * Returns user Dto page by given page number, page size, sort field and filter pattern.
     *
     * @param pageable      must contain positive page number, positive page size and existing sort field.
     * @param filterPattern is appropriate if it takes place at the beginning, middle or end of user username
     *                      or equals to user identifier.
     * @return task Dto page by given page number, page size, sort field and filter pattern.
     * @throws org.hibernate.exception.SQLGrammarException when sorting field does not exist.
     * @throws IllegalArgumentException                    when page number is negative.
     */
    Page<UserDto> getPageOfUsers(Pageable pageable, String filterPattern);
}
