package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.repositories.ReviewRepository;
import com.revature.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create-review")
    public Review createItem(@RequestBody Review review){
        System.out.println(review);
        return reviewService.createReview(review);
    }
    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewsByProduct(@PathVariable("id") Integer id){
        return ResponseEntity.ok(reviewService.getReviewsByProduct(id));

    }
}
