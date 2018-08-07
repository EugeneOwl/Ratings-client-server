package com.example.server.transformer;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleTransformer implements Transformer<Role, RoleDto> {
    @Override
    public RoleDto transform(final Role role) {
        final RoleDto roleDto =  RoleDto.builder()
                .label(role.getLabel())
                .build();
        roleDto.setId(role.getId());

        return roleDto;
    }

    @Override
    public Role transform(final RoleDto roleDto) {
        if (Objects.isNull(roleDto)) {
            return null;
        }
        final Role rating = Role.builder()
                .label(roleDto.getLabel())
                .build();
        rating.setId(roleDto.getId());

        return rating;
    }
}
