package com.example.server.service;

import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import org.junit.After;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RoleServiceImplTest {

    @TestConfiguration
    static class Config {

        @Bean
        public RoleService roleService() {

            return new RoleServiceImpl();
        }
    }

    private Role role;

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        role = new Role();
    }

    @After
    public void tearDown() {
        role = null;
    }

    @Test
    public void getRoleById() {
        Role expectedRole = new Role();
        expectedRole.setId(1);
        expectedRole.setValue("test value");

        when(roleRepository.existsById(expectedRole.getId()))
                .thenReturn(true);

        when(roleRepository.getOne(expectedRole.getId()))
                .thenReturn(expectedRole);

        Role actualRole = roleService.getRoleById(expectedRole.getId());

        assertEquals(expectedRole, actualRole);
    }

    @Test
    public void getAllRoles() {
        Role expectedRole = new Role();
        expectedRole.setId(1);
        expectedRole.setValue("test value");

        when(roleRepository.findAll((Sort.by("id"))))
                .thenReturn(Collections.singletonList(expectedRole));

        List<Role> actualRoles = roleService.getAllRoles();

        assertEquals(Collections.singletonList(expectedRole), actualRoles);
        verify(roleRepository, times(1)).findAll(Sort.by("id"));
    }
}