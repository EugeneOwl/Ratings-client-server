package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {
    int ROLE_COUNT_PER_PAGE = 2;

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    void addRole(RoleDto role);

    void updateRole(RoleDto roleDto);

    void removeRole(Long id);

    List<Role> getRoleListByIds(List<Long> ids);

    Page<RoleDto> getPageOfRoles(int page,
                                 String sortByColumn,
                                 String filterPattern);
}
