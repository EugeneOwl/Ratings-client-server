package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.dto.UserUpdateDto;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.transformer.UserTransformer;
import com.example.server.transformer.UserUpdateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private UserUpdateTransformer userUpdateTransformer;

    @Override
    public UserDto getUserById(final Long id) {
        if (userRepository.existsById(id)) {
            final User user = userRepository.getOne(id);
            user.setTasks(user.getTasks().stream().distinct().collect(Collectors.toList()));
            log.info("User was taken by id: " + user);

            return userTransformer.transform(user);
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void updateUser(final UserUpdateDto userUpdateDto) {
        final User user = Optional.of(userRepository.getOne(userUpdateDto.getId()))
                .orElseThrow(EntityNotFoundException::new);
        user.update(userUpdateTransformer.transform(userUpdateDto));

        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        final List<User> users = userRepository.findAll(Sort.by("id"));

        return users.stream()
                .peek(u -> log.info("User was taken: " + u))
                .map(userTransformer::transform)
                .collect(Collectors.toList());
    }
}
