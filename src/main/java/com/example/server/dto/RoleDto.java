package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Dto {
    private int id;
    private String value;
}
