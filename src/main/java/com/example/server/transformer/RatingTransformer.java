package com.example.server.transformer;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingTransformer implements Transformer<Rating, RatingDto> {
    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RatingDto transform(final Rating rating) {
        final RatingDto ratingDto = RatingDto.builder()
                .label(rating.getLabel())
                .mark(rating.getMark())
                .sender(userTransformer.transform(
                        userRepository.getOne(rating.getSender().getId()))
                )
                .recipient(userTransformer.transform(
                        userRepository.getOne(rating.getRecipient().getId()))
                )
                .build();
        ratingDto.setId(rating.getId());

        return ratingDto;
    }

    @Override
    public Rating transform(final RatingDto ratingDto) {
        final Rating rating = Rating.builder()
                .label(ratingDto.getLabel())
                .mark(ratingDto.getMark())
                .recipient(userRepository.getOne(ratingDto.getRecipient().getId()))
                .sender(userRepository.getOne(ratingDto.getSender().getId()))
                .build();
        rating.setId(ratingDto.getId());

        return rating;
    }
}
