package com.revature.services;

import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsByProduct (Integer productId) {
        return reviewRepository.getReviewsByProduct(productId);
    }

    public void createReview (Review review) {
        Product product = new Product();
        product.setId(Integer.parseInt(review.getProductId()));
        review.setProduct(product);
        reviewRepository.save(review);
    }


}
