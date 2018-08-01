package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;

import java.util.List;

public interface RoleService {

    RoleDto getRoleById(int id);

    List<RoleDto> getAllRoles();

    List<Role> getRoleListByIds(List<Integer> ids);

    void addRole(RoleDto role);

    void updateRole(RoleDto roleDto);

    void removeRole(int id);
}
