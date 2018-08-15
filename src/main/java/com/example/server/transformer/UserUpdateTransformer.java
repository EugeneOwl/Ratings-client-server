package com.example.server.transformer;

import com.example.server.dto.UserUpdateDto;
import com.example.server.model.User;
import com.example.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserUpdateTransformer implements UpdateTransformer<User, UserUpdateDto> {
    public static final String NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN = "[^0-9]";

    @Autowired
    private RoleService roleService;

    @Override
    public User transform(final UserUpdateDto userUpdateDto) {
        final User user = User.builder()
                .roles(new HashSet<>(roleService.getRoleListByIds(userUpdateDto.getRoleIds())))
                .mobileNumber(cleanUpMobileNumber(userUpdateDto.getMobileNumber()))
                .build();
        user.setId(userUpdateDto.getId());

        return user;
    }

    private String cleanUpMobileNumber(final String mobileNumber) {

        return mobileNumber.replaceAll(NOT_VALID_MOBILE_NUMBER_SYMBOL_PATTERN, "");
    }
}
