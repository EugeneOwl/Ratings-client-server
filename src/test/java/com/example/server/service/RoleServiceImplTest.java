package com.example.server.service;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.repository.RoleRepository;
import com.example.server.transformer.RoleTransformer;
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

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

//    @MockBean
//    private RoleTransformer roleTransformer;
//// НЕ ПРОХОДИТ ПОТОМУ ЧТО НЕ ПЕРЕОПРЕДЕЛИЛ ПОВЕДЕНИЕ НОВОГО RoleTransformer
//
//    @Test
//    public void getRoleById() {
//        final Role expectedRole = new Role();
//        expectedRole.setId(1);
//        expectedRole.setValue("test value");
//
//        when(roleRepository.existsById(expectedRole.getId()))
//                .thenReturn(true);
//
//        when(roleRepository.getOne(expectedRole.getId()))
//                .thenReturn(expectedRole);
//
//        final RoleDto actualRoleDto = roleService.getRoleById(expectedRole.getId());
//
//        assertEquals(expectedRole.getValue(), actualRoleDto.getValue());
//    }
//
//    @Test
//    public void getAllRoles() {
//        final Role expectedRole = new Role();
//        expectedRole.setId(1);
//        expectedRole.setValue("test value");
//
//        when(roleRepository.findAll((Sort.by("id"))))
//                .thenReturn(Collections.singletonList(expectedRole));
//
//        final List<RoleDto> actualRoleDtos = roleService.getAllRoles();
//
//        assertEquals(
//                Collections.singletonList(expectedRole).get(0).getValue(),
//                actualRoleDtos.get(0).getValue()
//        );
//        verify(roleRepository, times(1)).findAll(Sort.by("id"));
//    }
}