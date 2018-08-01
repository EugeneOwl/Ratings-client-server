package com.example.server.transformer;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleTransformer implements Transformer<Role, RoleDto> {
    @Override
    public RoleDto transform(final Role role) {
        final RoleDto roleDto =  RoleDto.builder()
                .value(role.getValue())
                .build();
        roleDto.setId(role.getId());

        return roleDto;
    }

    @Override
    public Role transform(final RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        final Role rating = Role.builder()
                .value(roleDto.getValue())
                .build();
        rating.setId(roleDto.getId());

        return rating;
    }
}
