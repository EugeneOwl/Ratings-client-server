package com.example.server.dto;

import com.example.server.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto implements Dto {
    private int id;
    private String value;
    private String rawSender;
    private String rawRecipient;
    private User sender;
    private User recipient;
}
