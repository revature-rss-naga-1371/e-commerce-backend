package com.revature.services;

import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    private ReviewRepository reviewRepositoryMock;
    private ReviewService reviewService;

    @BeforeEach
    public void setUp(){
        reviewRepositoryMock = mock(ReviewRepository.class);
        when(reviewRepositoryMock.getReviewsByProduct(1))
                .thenReturn(List.of(new Review(1,5,"Javier","Good",(new Product()),"1")));
        reviewService = new ReviewService(reviewRepositoryMock);
    }

    @Test
    public void getReviewsByProductTest (){
        List<Review> reviews;
        reviews = reviewService.getReviewsByProduct(1);
        assertEquals(1, reviews.size());
        assertThat(reviews, hasItem(new Review(1,5,"Javier","Good",(new Product()),"1")));
    }
    @Test
    public void createReviewTest(){
        Review review = new Review(1,5,"Javier","Good",(new Product()),"1");
        reviewService.createReview(review);
        verify(reviewRepositoryMock).save(review);
    }

}
