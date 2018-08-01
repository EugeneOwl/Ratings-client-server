package com.example.server.transformer;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingTransformer implements Transformer<Rating, RatingDto> {
    @Override
    public RatingDto transform(final Rating rating) {
        final RatingDto ratingDto =  RatingDto.builder()
                .value(rating.getValue())
                .rawRecipient("" + rating.getRecipient().getId())
                .rawSender("" + rating.getSender().getId())
                .sender(rating.getSender())
                .recipient(rating.getRecipient())
                .build();
        ratingDto.setId(rating.getId());

        return ratingDto;
    }

    @Override
    public Rating transform(final RatingDto ratingDto) {
        final Rating rating = Rating.builder()
                .value(ratingDto.getValue())
                .recipient(ratingDto.getRecipient())
                .sender(ratingDto.getSender())
                .build();
        rating.setId(ratingDto.getId());

        return rating;
    }
}
