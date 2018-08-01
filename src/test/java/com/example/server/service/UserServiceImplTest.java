package com.example.server.service;

import com.example.server.dto.UserDto;
import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.transformer.UserTransformer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
//@JsonTest
public class UserServiceImplTest {

    @TestConfiguration
    static class Config {

        @Bean
        public UserService userService() {

            return new UserServiceImpl();
        }
    }

    private UserDto userDto;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleService roleService;

    @MockBean
    private RawDataProcessor rawDataProcessor;

    @MockBean
    private UserTransformer userTransformer;

    @Before
    public void setUp() {
        userDto = new UserDto();
    }

    @After
    public void tearDown() {
        userDto = null;
    }

    @Test
    public void getUserById() {
        final User expectedUser = new User();
        expectedUser.setUsername("test username");
        expectedUser.setId(1);

        final UserDto expectedUserDto = UserDto.builder()
                .id(expectedUser.getId())
                .username(expectedUser.getUsername())
                .build();

        when(userTransformer.transform(expectedUser))
                .thenReturn(expectedUserDto);

        userDto = userTransformer.transform(expectedUser);

        when(userRepository.getOne(userDto.getId()))
                .thenReturn(expectedUser);
        when(userRepository.existsById(userDto.getId()))
                .thenReturn(true);

        final UserDto actualUserDto = userService.getUserById(userDto.getId());

        assertEquals(expectedUserDto,
                actualUserDto);
    }

    @Test
    public void addUser() {
        final Role role = new Role();
        role.setId(2);
        role.setValue("test role value");

        final User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setUsername("test username");
        expectedUser.setRoles(Collections.singleton(role));

        final UserDto expectedUserDto = UserDto.builder()
                .id(expectedUser.getId())
                .username(expectedUser.getUsername())
                .roles(new HashSet<>())
                .build();
        expectedUserDto.setRawRoles("2");

        when(rawDataProcessor.getNumericList(expectedUserDto.getRawRoles()))
                .thenReturn(Collections.singletonList(2));

        when(roleService.getRoleListByIds(Collections.singletonList(2)))
                .thenReturn(Collections.singletonList(role));

        when(userTransformer.transform(expectedUserDto))
                .thenReturn(expectedUser);

        userService.addUser(expectedUserDto);

        verify(userRepository, times(1)).save(expectedUser);
    }

    @Test
    public void updateUser() {
        addUser();
    }

    @Test
    public void removeUser() {
        userService.removeUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void getAllUsers() {
        final User expectedUser = new User();
        expectedUser.setUsername("test username");
        expectedUser.setId(1);

        final UserDto expectedUserDto = UserDto.builder()
                .id(expectedUser.getId())
                .username(expectedUser.getUsername())
                .build();

        when(userRepository.findAll(Sort.by("id")))
                .thenReturn(Collections.singletonList(expectedUser));

        when(userTransformer.transform(expectedUser))
                .thenReturn(expectedUserDto);

        final List<UserDto> actualUserDtos = userService.getAllUsers();

        assertEquals(expectedUserDto,
                actualUserDtos.get(0));

        verify(userRepository, times(1)).findAll(Sort.by("id"));
    }

    @Test
    public void isUserValid() {
        userDto = null;
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto = new UserDto();
        userDto.setUsername("");
        userDto.setPassword("test password");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("test username");
        userDto.setPassword("");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("  ");
        userDto.setPassword("      ");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("test username");
        userDto.setPassword("test password");
        Assert.assertTrue(userService.isUserValid(userDto));
    }
}