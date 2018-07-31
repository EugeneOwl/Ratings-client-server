package com.example.server.repository;

import com.example.server.model.Rating;
import com.example.server.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RatingRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void getRatingsByRecipient() {
        User recipient = new User();
        recipient.setUsername("test recipient username");
        User sender = new User();
        sender.setUsername("test sender username");

        Rating rating = new Rating();
        rating.setValue("test rating value");
        rating.setRecipient(recipient);
        rating.setSender(sender);

        testEntityManager.persist(sender);
        testEntityManager.persist(recipient);
        testEntityManager.persist(rating);
        testEntityManager.flush();

        List<Rating> expectedRatingList = Collections.singletonList(rating);
        List<Rating> actualRatingList = ratingRepository.getRatingsByRecipient(recipient);

        Assert.assertEquals(
                expectedRatingList.get(0).getValue(),
                actualRatingList.get(0).getValue()
        );
    }

}