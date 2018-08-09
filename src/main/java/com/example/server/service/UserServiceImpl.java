package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public UserDto getUserById(final Long id) {
        if (userRepository.existsById(id)) {
            final User user = userRepository.getOne(id);
            log.info("User was taken by id: " + user);

            return userTransformer.transform(user);
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void addUser(final UserDto userDto) {
        final User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was added: " + user);
    }

    @Override
    public void updateUser(final UserDto userDto) {
        final User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public void removeUser(final Long id) {
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

        return (Objects.nonNull(userDto)
                && StringUtils.isNotBlank(userDto.getUsername())
                && isMobileNumberCorrect(userDto.getMobileNumber())
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

    private boolean isMobileNumberCorrect(final String number) {
        return Pattern.compile("^((375)([0-9]{9}))$").matcher(number).matches();
    }
}
