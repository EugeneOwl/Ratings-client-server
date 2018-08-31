package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.security.exception.ForbiddenOperationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    /**
     * Returns role Dto of role with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return role Dto of role with the given identifier.
     * @throws javax.persistence.EntityNotFoundException if no role exists for given id.
     */
    RoleDto getRoleById(Long id);

    /**
     * Returns role Dto list of all roles from database sorted by identifier.
     *
     * @return role Dto list of all roles from database sorted by identifier.
     */
    List<RoleDto> getAllRoles();

    /**
     * Saves new role received from given role Dto.
     *
     * @param roleDto
     */
    void addRole(RoleDto roleDto);

    /**
     * Updates existing role received from given role Dto.
     *
     * @param roleDto must have existing role identifier
     *                and not belong to permanent role list.
     * @throws ForbiddenOperationException               if role received from given role Dto
     *                                                   belong to permanent role list.
     * @throws javax.persistence.EntityNotFoundException if no role exists for given role Dto.
     * @see com.example.server.model.PermanentRoles
     */
    void updateRole(RoleDto roleDto) throws ForbiddenOperationException;

    /**
     * Removes existing role from database by given identifier.
     *
     * @param id must not be {@literal null}.
     * @throws ForbiddenOperationException               if role received from given role Dto
     *                                                   belong to permanent role list.
     * @throws javax.persistence.EntityNotFoundException if no role exists for given id.
     * @see com.example.server.model.PermanentRoles
     */
    void removeRole(Long id) throws ForbiddenOperationException;

    /**
     * Returns role list by given list of identifiers sorted by identifiers.
     *
     * @param ids must not be {@literal null}.
     * @return role list by given list of identifiers sorted by identifiers.
     * @throws javax.persistence.EntityNotFoundException if any role does not exist
     *                                                   from given identifiers list.
     */
    List<Role> getRoleListByIds(List<Long> ids);

    /**
     * Returns role Dto page by given page number, page size, sort field and filter pattern.
     *
     * @param pageable      must contain positive page number, positive page size and existing sort field.
     * @param filterPattern is appropriate if it takes place at the beginning, middle or end of role label
     *                      or equals to role identifier.
     * @return role Dto page by given page number, page size, sort field and filter pattern.
     * @throws org.hibernate.exception.SQLGrammarException when sorting field does not exist.
     * @throws IllegalArgumentException when page number is negative.
     */
    Page<RoleDto> getPageOfRoles(Pageable pageable,
                                 String filterPattern);
}
