package com.example.server.population;

import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public DataLoader(final RoleRepository roleRepository, final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.saveAll(getInitialRoles());
        }
        if (userRepository.findAll().isEmpty()) {
            userRepository.saveAll(getInitialUsers());
        }
    }

    private List<Role> getInitialRoles() {
        return Arrays.asList(
                new Role("User", new HashSet<>()),
                new Role("Admin", new HashSet<>()),
                new Role("Anonymous", new HashSet<>())
        );
    }

    private List<User> getInitialUsers() {
        return Arrays.asList(
                new User("Eugene", "password",
                        new HashSet<>(), new ArrayList<>(), new ArrayList<>()),
                new User("Alex", "pwd123",
                        new HashSet<>(), new ArrayList<>(), new ArrayList<>()),
                new User("Victor", "54321pass",
                        new HashSet<>(), new ArrayList<>(), new ArrayList<>())
        );
    }
}