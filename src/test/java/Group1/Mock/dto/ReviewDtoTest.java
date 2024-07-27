package Group1.Mock.dto;

import Group1.Mock.dto.ReviewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewDtoTest {

    private ReviewDto reviewDto;

    @BeforeEach
    public void setUp() {
        reviewDto = new ReviewDto();
    }

    @Test
    public void testNoArgsConstructor() {
        ReviewDto dto = new ReviewDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        long reviewId = 1L;
        String comment = "Great course!";
        int rating = 5;
        Long coursesId = 2L;
        long userId = 3L;

        ReviewDto dto = new ReviewDto(reviewId, comment, rating, coursesId, userId);

        assertEquals(reviewId, dto.getReviewId());
        assertEquals(comment, dto.getComment());
        assertEquals(rating, dto.getRating());
        assertEquals(coursesId, dto.getCoursesId());
        assertEquals(userId, dto.getUserId());
    }

    @Test
    public void testGettersAndSetters() {
        long reviewId = 1L;
        String comment = "Great course!";
        int rating = 5;
        Long coursesId = 2L;
        long userId = 3L;

        reviewDto.setReviewId(reviewId);
        reviewDto.setComment(comment);
        reviewDto.setRating(rating);
        reviewDto.setCoursesId(coursesId);
        reviewDto.setUserId(userId);

        assertEquals(reviewId, reviewDto.getReviewId());
        assertEquals(comment, reviewDto.getComment());
        assertEquals(rating, reviewDto.getRating());
        assertEquals(coursesId, reviewDto.getCoursesId());
        assertEquals(userId, reviewDto.getUserId());
    }
}
