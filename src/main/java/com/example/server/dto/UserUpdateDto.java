package com.example.server.dto;

import com.example.server.service.UserService;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto implements Dto {
    private Long id;

    @Pattern(regexp = UserService.MOBILE_NUMBER_PATTERN,
            message = "Mobile number should match.")
    private String mobileNumber;

    private List<Long> roleIds = new ArrayList<>();
}
