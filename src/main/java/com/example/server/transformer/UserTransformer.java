package com.example.server.transformer;

import com.example.server.dto.UserDto;
import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserTransformer implements Transformer<User, UserDto> {

    @Autowired
    private TaskTransformer taskTransformer;

    @Autowired
    private RoleTransformer roleTransformer;

    @Override
    public User transform(final UserDto userDto) {

        return null;
    }

    @Override
    public UserDto transform(final User user) {

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .mobileNumber(user.getMobileNumber())
                .roles(user.getRoles().stream().map(roleTransformer::transform)
                        .collect(Collectors.toSet()))
                .tasks(user.getTasks().stream()
                        .map(taskTransformer::transform)
                        .collect(Collectors.toList()))
                .build();
    }
}
