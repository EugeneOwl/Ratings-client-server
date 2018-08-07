package com.example.server.transformer;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingTransformer implements Transformer<Rating, RatingDto> {
    @Autowired
    private UserTransformer userTransformer;

    @Override
    public RatingDto transform(final Rating rating) {
        final RatingDto ratingDto =  RatingDto.builder()
                .label(rating.getLabel())
                .mark(rating.getMark())
                .sender(userTransformer.transform(rating.getSender()))
                .recipient(userTransformer.transform(rating.getRecipient()))
                .build();
        ratingDto.setId(rating.getId());

        return ratingDto;
    }

    @Override
    public Rating transform(final RatingDto ratingDto) {
        final Rating rating = Rating.builder()
                .label(ratingDto.getLabel())
                .mark(ratingDto.getMark())
                .recipient(userTransformer.transform(ratingDto.getRecipient()))
                .sender(userTransformer.transform(ratingDto.getSender()))
                .build();
        rating.setId(ratingDto.getId());

        return rating;
    }
}
