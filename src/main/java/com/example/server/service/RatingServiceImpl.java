package com.example.server.service;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import com.example.server.repository.RatingRepository;
import com.example.server.transformer.RatingTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingTransformer ratingTransformer;

    @Override
    public RatingDto getRatingById(final Long id) {
        final Rating rating = ratingRepository.getOne(id);
        log.info("Rating was taken by id: {}", rating);

        return ratingTransformer.transform(rating);
    }

    @Override
    public void addRating(final RatingDto ratingDto) {
        final Rating rating = ratingTransformer.transform(ratingDto);
        ratingRepository.save(rating);
        log.info("Rating was added: {}", rating);
    }

    @Override
    public List<RatingDto> getRatingsByRecipient(final Long recipientId) {
        final List<Rating> ratings = ratingRepository.getRatingsByRecipientId(recipientId);

        return ratings.stream()
                .peek(rating -> log.info(
                        "Rating = {} was taken by recipient = {}",
                        rating,
                        rating.getRecipient()
                ))
                .map(ratingTransformer::transform)
                .collect(Collectors.toList());
    }
}
