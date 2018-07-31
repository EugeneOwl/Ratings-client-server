package com.example.server.service;

import com.example.server.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(int id);

    List<Role> getAllRoles();

    List<Role> getRoleListByIds(List<Integer> ids);
}
