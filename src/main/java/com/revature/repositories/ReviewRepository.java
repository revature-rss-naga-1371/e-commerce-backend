package com.revature.repositories;

import com.revature.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select u from Review u where u.product.id= ?1")
    List<Review> getReviewsByProduct (Integer product_id);
}
