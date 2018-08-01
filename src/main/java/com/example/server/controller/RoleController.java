package com.example.server.controller;

import com.example.server.dto.RoleDto;
import com.example.server.model.Role;
import com.example.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("server/roles")
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public RoleDto getById(@PathVariable("id") int id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        HttpHeaders headers = new HttpHeaders();

        if (roleDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.roleService.addRole(roleDto);
        return new ResponseEntity<>(roleDto, headers, HttpStatus.CREATED);
    }
}