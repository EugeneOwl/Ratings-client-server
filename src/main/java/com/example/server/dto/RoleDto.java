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

    @NotBlank(message = "Label can not be blank.")
    private String label;
}
