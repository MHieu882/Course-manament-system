package Group1.Mock.Mapper;

import Group1.Mock.dto.ReviewDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Review;
import Group1.Mock.entity.User;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CourseRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;

public class ReviewMapper {

    private static CourseRepository courseRepository;

    public static ReviewDto mapToReviewDto(Review review) {

        return new ReviewDto(
          review.getReviewId(),
          review.getComment(),
          review.getRating(),
          review.getCourses().getCourseId(),review.getUser().getId()
        );
    }

    public static Review mapToReview(ReviewDto reviewDto) {

        Course course = courseRepository.findById(reviewDto.getCoursesId()).orElseThrow(
                ()-> new ResourceNotFoundException("Course not found with id: " + reviewDto.getCoursesId())
        );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return new Review(
                reviewDto.getReviewId(),
                reviewDto.getComment(),
                reviewDto.getRating(),
                new Date(),
                user,
                course
        );
    }
}
