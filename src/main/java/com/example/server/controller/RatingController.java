package com.example.server.controller;

import com.example.server.dto.RatingDto;
import com.example.server.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "server/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("{id}")
    public RatingDto getRatingById(@PathVariable("id") final Long id) {

        return ratingService.getRatingById(id);
    }

    @GetMapping("recipient/{recipient_id}")
    public List<RatingDto> getRatingsByRecipient(@PathVariable("recipient_id") final Long recipientId) {

        return ratingService.getRatingsByRecipient(recipientId);
    }

    @PostMapping
    public RatingDto addRating(@Valid @RequestBody final RatingDto ratingDto) {
        this.ratingService.addRating(ratingDto);

        return ratingDto;
    }
}
