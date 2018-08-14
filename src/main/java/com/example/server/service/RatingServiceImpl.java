package com.example.server.service;

import com.example.server.dto.RatingDto;
import com.example.server.model.Rating;
import com.example.server.model.User;
import com.example.server.repository.RatingRepository;
import com.example.server.transformer.RatingTransformer;
import com.example.server.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingTransformer ratingTransformer;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public RatingDto getRatingById(final Long id) {
        if (ratingRepository.existsById(id)) {
            final Rating rating = ratingRepository.getOne(id);
            log.info("Rating was taken by id: " + rating);

            return ratingTransformer.transform(rating);
        }
        log.info("Attempt to take not existing rating with id = {}", id);

        return null;
    }

    @Override
    public void addRating(final RatingDto ratingDto) {
        final Rating rating = ratingTransformer.transform(ratingDto);

        if (isRatingValid(rating)) {
            ratingRepository.save(rating);
            log.info("Rating was added: " + rating);
        }
    }

    @Override
    public void removeRating(final Long id) {
        ratingRepository.deleteById(id);
        log.info("Rating with id = {} was removed: ", id);
    }

    @Override
    public List<RatingDto> getRatingsByRecipient(final Long recipientId) {
        final User recipient = userTransformer.transform(userService.getUserById(recipientId));
        final List<Rating> ratings = ratingRepository.getRatingsByRecipient(recipient);
        for (final Rating rating : ratings) {
            log.info("Rating = {} was taken by recipient = {}", rating, recipient);
        }

        return ratings.stream()
                .map(ratingTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isRatingValid(final Rating rating) {
        return (Objects.nonNull(rating.getRecipient())
                && Objects.nonNull(rating.getSender())
                && ! rating.getRecipient().equals(rating.getSender())
                && StringUtils.isNotBlank(rating.getLabel()));
    }
}
