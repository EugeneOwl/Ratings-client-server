package com.example.server.service;

import com.example.server.service.functions.GetLongFromStringKt;
import com.example.server.dto.RoleDto;
import com.example.server.model.PermanentRoles;
import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.security.exception.ForbiddenOperationException;
import com.example.server.transformer.RoleTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleTransformer roleTransformer;

    @Override
    public RoleDto getRoleById(final Long id) {
        final Role role = roleRepository.getOne(id);
        log.info("Role was taken by id: " + role);

        return roleTransformer.transform(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        final List<Role> list = roleRepository.findAll(Sort.by("id"));

        return list.stream()
                .peek(r -> log.info("Role was taken: {}", r))
                .map(roleTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public void addRole(final RoleDto roleDto) {
        final Role role = roleTransformer.transform(roleDto);
        roleRepository.save(role);
        log.info("Role was added: " + role);
    }

    @Override
    public void updateRole(final RoleDto roleDto) {
        final Role role = roleRepository.getOne(roleDto.getId());
        if (isRolePermanent(role.getLabel())) {
            throw new ForbiddenOperationException(
                    "Attempt to change permanent role: " + role.getLabel()
            );
        }

        role.setLabel(roleDto.getLabel());
        roleRepository.save(role);
        log.info("Role was updated: " + role);
    }

    @Override
    public void removeRole(final Long id) {
        final Role role = roleRepository.getOne(id);
        if (isRolePermanent(role.getLabel())) {
            throw new ForbiddenOperationException(
                    "Attempt to remove permanent role: " + role.getLabel()
            );
        }

        roleRepository.deleteById(id);
        log.info("Role with id = {} was removed: ", id);
    }

    @Override
    public List<Role> getRoleListByIds(final List<Long> ids) {
        return ids.stream().map(this::getRoleById)
                .filter(Objects::nonNull).map(roleTransformer::transform)
                .sorted(comparing(Role::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Page<RoleDto> getPageOfRoles(final Pageable pageable,
                                        final String filterPattern) {

        return roleRepository.findByIdOrLabel(
                GetLongFromStringKt.getLongFromString(filterPattern),
                filterPattern.toLowerCase(),
                pageable
        ).map(roleTransformer::transform);
    }

    private boolean isRolePermanent(final String roleLabel) {

        return Arrays.stream(PermanentRoles.values())
                .map(PermanentRoles::toString).anyMatch(roleLabel::equals);
    }
}
