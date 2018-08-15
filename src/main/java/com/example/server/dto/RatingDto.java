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
public class RatingDto implements Dto {
    private Long id;

    @NotBlank(message = "Label can not be blank.")
    private String label;

    @PositiveOrZero(message = "Mark should be positive or zero.")
    private byte mark;

    @NotNull(message = "Rating should have it's sender.")
    private UserDto sender;

    @NotNull(message = "Rating should have it's recipient.")
    private UserDto recipient;
}
