package com.example.server.dto;

import com.example.server.model.Role;
import com.example.server.model.Task;
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
    private Set<Role> roles = new HashSet<>();
    private List<TaskDto> tasks = new ArrayList<>();
}
