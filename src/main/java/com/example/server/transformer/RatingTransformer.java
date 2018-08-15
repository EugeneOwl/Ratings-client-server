package com.example.server.transformer;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
                        Optional.of(userRepository.getOne(rating.getSender().getId()))
                                .orElseThrow(EntityNotFoundException::new)
                ))
                .recipient(userTransformer.transform(
                        Optional.of(userRepository.getOne(rating.getRecipient().getId()))
                                .orElseThrow(EntityNotFoundException::new))
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
                .recipient(Optional.of(userRepository.getOne(ratingDto.getRecipient().getId()))
                        .orElseThrow(EntityNotFoundException::new))
                .sender(Optional.of(userRepository.getOne(ratingDto.getSender().getId()))
                        .orElseThrow(EntityNotFoundException::new))
                .build();
        rating.setId(ratingDto.getId());

        return rating;
    }
}
