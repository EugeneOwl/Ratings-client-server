package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.transformer.RoleTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        if (roleRepository.existsById(id)) {
            final Role role = roleRepository.getOne(id);
            log.info("Role was taken by id: " + role);
            return roleTransformer.transform(role);
        }
        log.info("Attempt to take not existing role with id = {}", id);

        return null;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        final List<Role> list = roleRepository.findAll(Sort.by("id"));
        for (final Role role : list) {
            log.info("Role was taken: " + role);
        }

        return list.stream().map(roleTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> getRoleListByIds(final List<Long> ids) {
        return ids.stream().map(this::getRoleById)
                .filter(Objects::nonNull).map(roleTransformer::transform)
                .sorted(comparing(Role::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void addRole(final RoleDto roleDto) {
        if (!isRoleValid(roleDto)) {

            return;
        }
        final Role role = roleTransformer.transform(roleDto);
        roleRepository.save(role);
        log.info("Role was added: " + role);
    }

    @Override
    public void updateRole(final RoleDto roleDto) {
        if (!isRoleValid(roleDto)) {

            return;
        }
        final Role role = roleTransformer.transform(roleDto);
        role.setUsers(roleRepository.getOne(role.getId()).getUsers());
        roleRepository.save(role);
        log.info("Role was updated: " + role);
    }

    @Override
    public void removeRole(final Long id) {
        final RoleDto roleDto = getRoleById(id);
        if (Objects.isNull(roleDto)) {

            return;
        }
        roleRepository.deleteById(id);
        log.info("Role with id = {} was removed: ", id);
    }

    @Override
    public boolean isRoleValid(final RoleDto roleDto) {
        return Objects.nonNull(roleDto)
                && StringUtils.isNotBlank(roleDto.getLabel());
    }
}
