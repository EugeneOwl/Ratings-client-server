package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Dto {
    private Long id;
    private String label;
    private byte mark;
    private UserDto sender;
    private UserDto recipient;
}
