package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RawDataProcessor rawDataProcessor;

    @Override
    public UserDto getUserById(final int id) {
        if (userRepository.existsById(id)) {
            final User user = userRepository.getOne(id);
            log.info("User was taken by id: " + user);

            return userTransformer.transform(user);
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void addUser(UserDto userDto) {
        userDto = updateRolesFromRawRoles(userDto);
        final User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was added: " + user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        userDto = updateRolesFromRawRoles(userDto);
        final User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public void removeUser(final int id) {
        userRepository.deleteById(id);
        log.info("User with id = {} was removed: ", id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        final List<User> list = userRepository.findAll(Sort.by("id"));
        for (final User user : list) {
            log.info("User was taken: " + user);
        }

        return list.stream()
                .map(userTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserValid(final UserDto userDto) {

        return (Objects.nonNull(userDto) &&
                StringUtils.isNotBlank(userDto.getUsername()) &&
                StringUtils.isNotBlank(userDto.getPassword())
        );
    }

    @Override
    public boolean addOrUpdateUserIfValid(final UserDto userDto) {
        if (isUserValid(userDto)) {
            if (userDto.getId() == 0) {
                addUser(userDto);
            } else {
                updateUser(userDto);
            }

            return true;
        }

        return false;
    }

    private UserDto updateRolesFromRawRoles(final UserDto userDto) {
        final List<Integer> roleIds = rawDataProcessor.getNumericList(userDto.getRawRoles());
        final List<Role> roles = roleService.getRoleListByIds(roleIds);
        userDto.setRoles(new HashSet<>());
        for (final Role role : roles) {
            userDto.addRole(role);
        }

        return userDto;
    }
}
