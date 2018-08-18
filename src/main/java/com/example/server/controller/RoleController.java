package com.example.server.controller;

import com.example.server.dto.RoleDto;
import com.example.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "server/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDto> getAllRoles() {

        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public RoleDto getRoleById(@PathVariable("id") final Long id) {

        return roleService.getRoleById(id);
    }

    @GetMapping("/page")
    public Page<RoleDto> getPageOfRoles(
            @RequestParam(value = "pageNumber", defaultValue = "0") final int pageNumber,
            @RequestParam(value = "sortByColumn", defaultValue = "id") final String sortByColumn,
            @RequestParam(value = "filterPattern", defaultValue = "") final String filterPattern
    ) {
        return roleService.getPageOfRoles(pageNumber, sortByColumn, filterPattern);
    }

    @PostMapping
    public RoleDto addRole(@Valid @RequestBody final RoleDto roleDto) {
        this.roleService.addRole(roleDto);

        return roleDto;
    }

    @PutMapping
    public RoleDto updateRole(@Valid @RequestBody final RoleDto roleDto) {
        this.roleService.updateRole(roleDto);

        return roleDto;
    }

    @DeleteMapping("{id}")
    public Long removeRole(@PathVariable("id") final Long id) {
        roleService.removeRole(id);

        return id;
    }
}