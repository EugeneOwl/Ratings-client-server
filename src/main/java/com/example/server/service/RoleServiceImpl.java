package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.transformer.RoleTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        final Role role = Optional.of(roleRepository.getOne(id))
                .orElseThrow(EntityNotFoundException::new);
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
        final Role role = Optional.of(roleRepository.getOne(roleDto.getId()))
                .orElseThrow(EntityNotFoundException::new);
        role.setLabel(roleDto.getLabel());
        roleRepository.save(role);
        log.info("Role was updated: " + role);
    }

    @Override
    public void removeRole(final Long id) {
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
}
