package com.example.server.transformer;

import com.example.server.dto.UserListDto;
import com.example.server.model.User;
import org.springframework.stereotype.Component;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Component
public class UserListTransformer {

    public UserListDto makeDto(final User user) {
        final UserListDto dto = new UserListDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());

        return dto;
    }
}
