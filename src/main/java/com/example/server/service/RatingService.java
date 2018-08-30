package com.example.server.service;

import com.example.server.dto.RatingDto;

import java.util.List;

public interface RatingService {

    /**
     * Returns rating Dto of rating with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return rating Dto of rating with the given identifier.
     * @throws javax.persistence.EntityNotFoundException if no rating exists for given id
     *                                                   or if no user exists for received rating sender or recipient.
     */
    RatingDto getRatingById(Long id);

    /**
     * Saves new rating received from given rating Dto.
     *
     * @param ratingDto must have user with existing id as sender and recipient.
     * @throws javax.persistence.EntityNotFoundException if no user exists for received rating sender or recipient.
     */
    void addRating(RatingDto ratingDto);

    /**
     * Returns rating Dto list of user with the given identifier.
     *
     * @param recipientId must not be {@literal null}.
     * @return rating Dto list of user with the given identifier.
     * @throws javax.persistence.EntityNotFoundException if no user exists for any rating sender or recipient of received rating list.
     */
    List<RatingDto> getRatingsByRecipient(final Long recipientId);
}
