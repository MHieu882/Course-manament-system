package Group1.Mock.Mapper;

import Group1.Mock.dto.CourseDto;
import Group1.Mock.entity.*;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CategoryRepository;
import Group1.Mock.reponsitory.OrderRepository;
import Group1.Mock.reponsitory.ReviewRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class CourseMapper {
    private static CategoryRepository categoryRepository;
    private static UserRepository userRepository;
    private static ReviewRepository reviewRepository;
    private static OrderRepository orderRepository;

    public static CourseDto mapToCourseDto(Course course) {
        Set<Long> categoryIds = new HashSet<>();
        for (Category category: course.getCategory()) {
            categoryIds.add(category.getCategoryId());
        }

        //intructor
        Set<Long> instructorIds=new HashSet<>();
        for (User user : course.getInstructor()) {
            instructorIds.add(user.getId());
        }
        //review
        Set<Long> reviewIds=new HashSet<>();
        for (Review review : course.getReviews()) {
            reviewIds.add(review.getReviewId());
        }


        return new CourseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getDescription(),
                course.getPrice(),
                course.getCreatedAt(),
                course.getUpdatedAt(),
                course.isApproved(),
                course.getCourseLevel(),
                course.getLanguage(),
                categoryIds,
                instructorIds,
                reviewIds
        );
    }

    public static Course mapToCourse(CourseDto courseDto) throws Exception {
        Set<Category> categories = new HashSet<>();
        for (Long categoryId: courseDto.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);

            category.ifPresent(categories::add);
        }

        Set<User> instructors = new HashSet<>();
        for(Long userId : courseDto.getInstructorIds()) {
            Optional<User> user = userRepository.findById(userId);
            user.orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));


            user.ifPresent(u -> {
                if(u.getRole().getName() == RoleEnum.INSTRUCTOR){
                    instructors.add(u);
                }
                else {
                    // Nếu không phải Instructor, ném ngoại lệ
                    throw new ResourceNotFoundException("User is not an INSTRUCTOR: " + userId);
                }
            });
        }
        Set<Review> reviews = new HashSet<>();
        for (Long reviewId: courseDto.getReviewIds()) {
            Optional<Review> review = reviewRepository.findById(reviewId);
            review.ifPresent(reviews::add);
        }

        return new Course(
                courseDto.getCourseId(),
                courseDto.getCourseName(),
                courseDto.getDescription(),
                courseDto.getPrice(),
                courseDto.getCreatedAt(),
                courseDto.getUpdatedAt(),
                courseDto.isApproved(),
                courseDto.getCourseLevel(),
                courseDto.getLanguage(),
                categories,
                instructors,
                reviews
        );
    }


}
