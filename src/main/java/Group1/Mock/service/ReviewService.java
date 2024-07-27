package Group1.Mock.service;


import Group1.Mock.Mapper.ReviewMapper;
import Group1.Mock.dto.ReviewDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Review;
import Group1.Mock.entity.User;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private CourseRepository courseRepository;

//    Xem tất cả review cua user
    public ResponseEntity<Page<ReviewDto>> getAllReviews(Pageable pageable) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Page<Review> reviews = reviewRepository.findByUserId(user.getId(),pageable);

        return ResponseEntity.ok(reviews.map(ReviewMapper::mapToReviewDto));
    }
//    Thêm review
public ResponseEntity<Page<ReviewDto>> viewReviewsCourse(Long courseId,Pageable pageable){

    Page<Review> reviews = reviewRepository.findByCoursesCourseId(courseId,pageable);
    return ResponseEntity.ok(reviews.map(ReviewMapper::mapToReviewDto));
}
    public ReviewDto createReview(ReviewDto reviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setUser(user);

        Course course = courseRepository.findById(reviewDto.getCoursesId()).orElseThrow(
                ()-> new ResourceNotFoundException("Course not found with id: " + reviewDto.getCoursesId())
        );


        review.setCourses(course);

        reviewRepository.save(review);

        return ReviewMapper.mapToReviewDto(review);

    }
}
