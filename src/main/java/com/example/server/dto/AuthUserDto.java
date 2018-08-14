package com.example.server.dto;

import com.example.server.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author i.katlinsky
 * @since 22.07.2016
 */
@Getter
@Setter
public class AuthUserDto implements Dto {
    private long id;
    private String username;
    private Set<Role> role;
}
