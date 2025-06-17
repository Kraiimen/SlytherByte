package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.ReviewDto;
import com.slytherin.slytherbyte.models.entities.Review;
import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() throws DataException {
        List<Review> reviews = reviewService.findAllReviews();
        List<ReviewDto> dtos = reviews.stream().map(review -> ReviewDto.toDto(review)).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/logged-user")
    public ResponseEntity<List<ReviewDto>> getAllForLoggedUser(@AuthenticationPrincipal UserAccount ua) throws DataException, EntityNotFoundException {
        List<Review> reviews = reviewService.findAllByProfileId(ua.getUserProfile().getUserProfileId());
        List<ReviewDto> dtos = reviews.stream().map(review -> ReviewDto.toDto(review)).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable("id") int reviewId) throws DataException, EntityNotFoundException {
        Review review = reviewService.findReviewById(reviewId);
        ReviewDto rDto = ReviewDto.toDto(review);
        return ResponseEntity.ok(rDto);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto dto) throws DataException, EntityNotFoundException {
        Review review = dto.toEntity();
        Review createdReview = reviewService.createReview(review, dto.gameId(), dto.userProfileId());
        return ResponseEntity.ok(ReviewDto.toDto(createdReview));
    }

    @PutMapping("/{id}")
    ResponseEntity<ReviewDto> updateReview(@PathVariable ("id") int reviewId, @RequestBody ReviewDto reviewDto) throws DataException, EntityNotFoundException{
        if(reviewDto.reviewId()!=reviewId){
            return ResponseEntity.badRequest().build();
        }
        Review review=reviewDto.toEntity();
        ReviewDto completeRDto=ReviewDto.toDto(reviewService.updateReview(review, reviewDto.gameId(), reviewDto.userProfileId()));
        return ResponseEntity.ok(completeRDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteReview(@PathVariable ("id") int reviewId) throws DataException, EntityNotFoundException {
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recent")
    ResponseEntity<List<ReviewDto>> getRecentReviews() throws DataException{
        List<Review> reviews=reviewService.findRecentReviews();
        List<ReviewDto> rDto=reviews.stream().map(ReviewDto::toDto).toList();
        return ResponseEntity.ok(rDto);
    }
}
