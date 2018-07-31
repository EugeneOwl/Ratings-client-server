package com.example.server.repository;

import com.example.server.model.Rating;
import com.example.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> getRatingsByRecipient(User recipient);
}
