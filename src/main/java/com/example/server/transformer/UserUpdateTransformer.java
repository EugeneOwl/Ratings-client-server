package com.example.server.transformer;

import com.example.server.dto.UserUpdateDto;
import com.example.server.model.User;
import com.example.server.service.RoleService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserUpdateTransformer implements UpdateTransformer<User, UserUpdateDto> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public User transform(final UserUpdateDto userUpdateDto) {
        final User user = new User();
        user.setRoles(new HashSet<>(roleService.getRoleListByIds(userUpdateDto.getRoleIds())));
        user.setMobileNumber(userService.cleanUpMobileNumber(userUpdateDto.getMobileNumber()));
        user.setId(userUpdateDto.getId());

        return user;
    }
}
