package com.example.server.service;

import com.example.server.dto.RatingDto;
import com.example.server.dto.UserDto;
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

    @Autowired
    private RawDataProcessor rawDataProcessor;

    @Override
    public RatingDto getRatingById(final int id) {
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
        final UserDto senderDto = getUserByRawId(ratingDto.getRawSender());
        final UserDto recipientDto = getUserByRawId(ratingDto.getRawRecipient());
        rating.setRecipient(userTransformer.transform(recipientDto));
        rating.setSender(userTransformer.transform(senderDto));

        if (isRatingValid(rating)) {
            ratingRepository.save(rating);
            log.info("Rating was added: " + rating);
        }
    }

    @Override
    public void removeRating(final int id) {
        ratingRepository.deleteById(id);
        log.info("Rating with id = {} was removed: ", id);
    }

    @Override
    public List<RatingDto> getRatingsByRecipient(final UserDto recipientDto) {
        final User recipient = userTransformer.transform(recipientDto);
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
        return (rating.getRecipient() != null &&
                rating.getSender() != null &&
                ! rating.getRecipient().equals(rating.getSender()) &&
                StringUtils.isNotBlank(rating.getValue()));
    }

    private UserDto getUserByRawId(final String rawId) {
        return userService.getUserById(
                rawDataProcessor.getNumeric(rawId)
        );
    }
}
