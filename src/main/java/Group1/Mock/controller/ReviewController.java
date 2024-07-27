package Group1.Mock.controller;

import Group1.Mock.dto.ReviewDto;
import Group1.Mock.entity.Review;
import Group1.Mock.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;


    @GetMapping
    public ResponseEntity<Page<ReviewDto>> getAllReviews(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewService.getAllReviews(pageable);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<Page<ReviewDto>> viewReviewsCourse(@PathVariable("courseId") Long courseId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewService.viewReviewsCourse(courseId,pageable);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto newReview = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
}
