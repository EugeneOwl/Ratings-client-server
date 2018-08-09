package com.example.server.service;

import com.example.server.dto.RatingDto;
import com.example.server.dto.UserDto;
import com.example.server.model.Rating;
import com.example.server.model.User;
import com.example.server.repository.RatingRepository;
import com.example.server.transformer.RatingTransformer;
import com.example.server.transformer.UserTransformer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RatingServiceImplTest {

    @TestConfiguration
    static class Config {

        @Bean
        public RatingService ratingService() {

            return new RatingServiceImpl();
        }
    }

    private Rating rating;

    @Autowired
    RatingService ratingService;

    @MockBean
    private RatingRepository ratingRepository;

    @MockBean
    private RatingTransformer ratingTransformer;

    @MockBean
    private UserService userService;

    @MockBean
    private UserTransformer userTransformer;

    @MockBean
    private RawDataProcessor rawDataProcessor;

    @Before
    public void setUp() {
        rating = new Rating();
    }

    @After
    public void tearDown() {
        rating = null;
    }

//    @Test
//    public void getRatingById() {
//        final Rating expectedRating = new Rating();
//        rating.setId(1);
//        rating.setValue("test rating value");
//
//        final RatingDto expectedRatingDto = RatingDto.builder()
//                .id(rating.getId())
//                .value(rating.getValue())
//                .build();
//
//        when(ratingRepository.existsById(expectedRatingDto.getId()))
//                .thenReturn(true);
//        when(ratingRepository.getOne(expectedRatingDto.getId()))
//                .thenReturn(expectedRating);
//        when(ratingTransformer.transform(expectedRating))
//                .thenReturn(expectedRatingDto);
//
//        final RatingDto actualRatingDto = ratingService.getRatingById(expectedRatingDto.getId());
//
//        assertEquals(expectedRatingDto, actualRatingDto);
//
//        verify(ratingRepository, times(1))
//                .existsById(expectedRatingDto.getId());
//        verify(ratingRepository, times(1))
//                .getOne(expectedRatingDto.getId());
//        verify(ratingTransformer, times(1))
//                .transform(expectedRating);
//    }
//
//    @Test
//    public void addRating() {
//        final User sender = new User();
//        sender.setId(2);
//        sender.setUsername("test sender username");
//
//        final UserDto senderDto = UserDto.builder()
//                .id(sender.getId())
//                .username(sender.getUsername())
//                .build();
//
//        final User recipient = new User();
//        recipient.setId(3);
//        recipient.setUsername("test recipient username");
//
//        final UserDto recipientDto = UserDto.builder()
//                .id(recipient.getId())
//                .username(recipient.getUsername())
//                .build();
//
//        final Rating expectedRating = new Rating();
//        expectedRating.setValue("test rating value");
//        expectedRating.setSender(sender);
//        expectedRating.setRecipient(recipient);
//
//        final RatingDto expectedRatingDto = RatingDto.builder()
//                .value(expectedRating.getValue())
//                .rawSender("2")
//                .rawRecipient("3")
//                .build();
//
//        when(ratingTransformer.transform(expectedRatingDto))
//                .thenReturn(expectedRating);
//
//        when(rawDataProcessor.getNumeric(expectedRatingDto.getRawSender()))
//                .thenReturn(senderDto.getId());
//        when(rawDataProcessor.getNumeric(expectedRatingDto.getRawRecipient()))
//                .thenReturn(recipientDto.getId());
//
//        when(userService.getUserById(senderDto.getId()))
//                .thenReturn(senderDto);
//        when(userService.getUserById(recipientDto.getId()))
//                .thenReturn(recipientDto);
//
//        when(userTransformer.transform(senderDto))
//                .thenReturn(sender);
//        when(userTransformer.transform(recipientDto))
//                .thenReturn(recipient);
//
//        ratingService.addRating(expectedRatingDto);
//
//        verify(ratingRepository).save(expectedRating);
//    }
//
//    @Test
//    public void removeRating() {
//        ratingService.removeRating(1);
//        verify(ratingRepository, times(1)).deleteById(1);
//    }
//
//    @Test
//    public void getRatingByRecipient() {
//        final Rating expectedRating = new Rating();
//        expectedRating.setValue("test rating value");
//
//        final RatingDto expectedRatingDto = RatingDto.builder()
//                .value(expectedRating.getValue())
//                .build();
//
//        final User recipient = new User();
//        recipient.setUsername("test recipient name");
//
//        final UserDto recipientDto = UserDto.builder()
//                .username(recipient.getUsername())
//                .build();
//
//        when(userTransformer.transform(recipientDto))
//                .thenReturn(recipient);
//        when(ratingRepository.getRatingsByRecipient(recipient))
//                .thenReturn(Collections.singletonList(expectedRating));
//        when(ratingTransformer.transform(expectedRating))
//                .thenReturn(expectedRatingDto);
//
//        final List<RatingDto> actualRatings = ratingService.getRatingsByRecipient(recipientDto);
//
//        assertEquals(Collections.singletonList(expectedRatingDto), actualRatings);
//
//        verify(ratingRepository).getRatingsByRecipient(recipient);
//    }
//
//    @Test
//    public void isRatingValid() {
//        final User user = new User();
//
//        rating.setValue("test value");
//        rating.setRecipient(null);
//        rating.setSender(user);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(user);
//        rating.setSender(null);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(null);
//        rating.setSender(null);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setRecipient(user);
//        rating.setSender(user);
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        final User otherUser = new User();
//        otherUser.setUsername("Other user");
//        rating.setSender(user);
//        rating.setRecipient(otherUser);
//        rating.setValue("   ");
//        Assert.assertFalse(ratingService.isRatingValid(rating));
//
//        rating.setValue("test value");
//        System.out.println(rating);
//        Assert.assertTrue(ratingService.isRatingValid(rating));
//    }
}