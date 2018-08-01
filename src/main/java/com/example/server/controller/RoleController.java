package com.example.server.controller;

import com.example.server.dto.RoleDto;
import com.example.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "server/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDto> getAllRoles() {

        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public RoleDto getById(@PathVariable("id") final int id) {

        return roleService.getRoleById(id);
    }

    @PutMapping
    public void addRole(@RequestBody final RoleDto roleDto) {
        if (roleDto == null) {
            return;
        }
        this.roleService.addRole(roleDto);
    }

    @PostMapping
    public void updateRole(@RequestBody final RoleDto roleDto) {
        if (roleDto == null) {
            return;
        }
        this.roleService.updateRole(roleDto);
    }

    @DeleteMapping("{id}")
    public void removeRole(@PathVariable("id") final int id) {
        final RoleDto roleDto = roleService.getRoleById(id);
        if (roleDto == null) {
            return;
        }
        roleService.removeRole(id);
    }
}