package com.example.server.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Min(value = 1, message = "Evaluation should be 1 hour minimum.")
    @Digits(integer = 4, fraction = 0, message = "Evaluation can not be longer than 4 digits")
    private short evaluation;

    private TaskDto parent;

    @NotNull(message = "Task should have it's user.")
    private UserDto user;
}
