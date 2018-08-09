package com.example.server.dto;

import com.example.server.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Dto for user list item.
 * @author d.krivenky
 * @since 27.08.2016
 */
@Getter
@Setter
public class UserListDto implements Dto {

    private long id;
    private String username;
    private Set<Role> roles;

}
