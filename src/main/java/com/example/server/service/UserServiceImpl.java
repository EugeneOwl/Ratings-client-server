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

import java.util.List;
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
    public UserDto getUserById(int id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getOne(id);
            log.info("User was taken by id: " + user);

            return userTransformer.transform(user);
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void addUser(UserDto userDto) {
        userDto = addRolesFromRawRoles(userDto);
        User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was added: " + user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        userDto = addRolesFromRawRoles(userDto);
        User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public void removeUser(int id) {
        userRepository.deleteById(id);
        log.info("User with id = {} was removed: ", id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> list = userRepository.findAll(Sort.by("id"));
        for (User user : list) {
            log.info("User was taken: " + user);
        }

        return list.stream()
                .map(userTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserValid(UserDto userDto) {
        return (userDto != null &&
                StringUtils.isNotBlank(userDto.getUsername()) &&
                StringUtils.isNotBlank(userDto.getPassword())
        );
    }

    @Override
    public boolean addOrUpdateUserIfValid(UserDto userDto) {
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

    private UserDto addRolesFromRawRoles(UserDto userDto) {
        List<Integer> roleIds = rawDataProcessor.getNumericList(userDto.getRawRoles());
        List<Role> roles = roleService.getRoleListByIds(roleIds);
        for (Role role : roles) {
            userDto.addRole(role);
        }

        return userDto;
    }
}
