package com.example.server.transformer;


import com.example.server.dto.AuthUserDto;
import com.example.server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author i.katlinsky
 * @since 22.07.2016
 */
@Component
@RequiredArgsConstructor
public class AuthUserTransformer {

    public AuthUserDto makeDto(final User user) {
        final AuthUserDto authUserDto = new AuthUserDto();

        authUserDto.setId(user.getId());
        authUserDto.setUsername(user.getUsername());
        authUserDto.setRole(user.getRoles());

        return authUserDto;
    }

}
