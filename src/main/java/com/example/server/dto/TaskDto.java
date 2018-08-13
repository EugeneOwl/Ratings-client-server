package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto implements Dto {
    private Long id;
    private String label;
    private String description;
    private short evaluation;
    private TaskDto parent;
    private UserDto user;
}
