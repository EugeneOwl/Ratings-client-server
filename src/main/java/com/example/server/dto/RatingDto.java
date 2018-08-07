package com.example.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Dto {
    private int id;
    private String label;
    private UserDto sender;
    private UserDto recipient;
}
