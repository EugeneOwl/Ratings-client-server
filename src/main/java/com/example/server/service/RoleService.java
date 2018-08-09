package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;

import java.util.List;

public interface RoleService {

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    List<Role> getRoleListByIds(List<Long> ids);

    void addRole(RoleDto role);

    void updateRole(RoleDto roleDto);

    void removeRole(Long id);
}
