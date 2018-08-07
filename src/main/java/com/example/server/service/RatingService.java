package com.example.server.service;

import com.example.server.dto.RatingDto;
import com.example.server.dto.UserDto;
import com.example.server.model.Rating;

import java.util.List;

public interface RatingService {

    RatingDto getRatingById(int id);

    void addRating(RatingDto ratingDto);

    void removeRating(int id);

    List<RatingDto> getRatingsByRecipient(final int recipientId);

    boolean isRatingValid(Rating rating);
}
