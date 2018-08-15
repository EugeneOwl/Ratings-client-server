package com.example.server.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Dto {
    private Long id;

    @NotBlank(message = "Role should have it's label.")
    private String label;
}
