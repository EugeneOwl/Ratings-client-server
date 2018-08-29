package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.security.exception.ForbiddenOperationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    void addRole(RoleDto role);

    void updateRole(RoleDto roleDto) throws ForbiddenOperationException;

    void removeRole(Long id);

    List<Role> getRoleListByIds(List<Long> ids);

    Page<RoleDto> getPageOfRoles(Pageable pageable,
                                 String filterPattern);
}
