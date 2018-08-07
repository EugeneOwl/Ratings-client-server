package com.example.server.controller;

import com.example.server.dto.RatingDto;
import com.example.server.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "server/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PutMapping
    public void addRating(@RequestBody final RatingDto ratingDto) {
        System.out.println("ADDING RATING CONTR: " + ratingDto.getLabel());
        if (Objects.isNull(ratingDto)) {

            return;
        }
        this.ratingService.addRating(ratingDto);
    }
}
