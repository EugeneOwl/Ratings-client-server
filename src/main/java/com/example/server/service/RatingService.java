package com.example.server.service;

import com.example.server.dto.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto getRatingById(Long id);

    void addRating(RatingDto ratingDto);

    List<RatingDto> getRatingsByRecipient(final Long recipientId);
}
