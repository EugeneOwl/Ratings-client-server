package com.example.server.transformer;

import com.example.server.dto.UserDto;
import com.example.server.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class UserTransformer implements Transformer<User, UserDto> {

    @Override
    public User transform(final UserDto userDto) {
        if (Objects.isNull(userDto)) {
            return null;
        }
        final User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .mobileNumber(userDto.getMobileNumber())
                .roles(userDto.getRoles())
                .build();
        user.setId(userDto.getId());
        user.setRatingsRecipient(new ArrayList<>());
        user.setRatingsSender(new ArrayList<>());

        return user;
    }

    @Override
    public UserDto transform(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .mobileNumber(user.getMobileNumber())
                .roles(user.getRoles())
                .build();
    }
}
