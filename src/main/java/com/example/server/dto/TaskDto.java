package com.example.server.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto implements Dto {
    private Long id;

    @NotBlank(message = "Label can not be blank.")
    private String label;

    @NotBlank(message = "Description can not be blank.")
    private String description;

    @PositiveOrZero(message = "Evaluation should be positive or zero.")
    private short evaluation;

    private TaskDto parent;

    @NotNull(message = "Task should have it's user.")
    private UserDto user;
}
