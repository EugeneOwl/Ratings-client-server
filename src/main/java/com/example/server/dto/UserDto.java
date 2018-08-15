package com.example.server.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Dto {
    private Long id;
    private String username;
    private String mobileNumber;
    private Set<RoleDto> roles = new HashSet<>();
    private List<TaskDto> tasks = new ArrayList<>();
}
