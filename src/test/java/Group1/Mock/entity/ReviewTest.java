package Group1.Mock.entity;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.Review;
import Group1.Mock.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    private Review review;
    private User user;
    private Course course;

    @BeforeEach
    public void setUp() {
        Role role = new Role();
        role.setId(1L);
        role.setName(RoleEnum.STUDENT);
        user = new User("1L", "John Doe", "johndoe@example.com", role);
        course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Test Course");
        course.setDescription("Learn Java from scratch");
        review = new Review(1L, "Great course!", 5, new Date(), user, course);
    }

    @Test
    public void testReviewId() {
        assertEquals(1L, review.getReviewId());
    }

    @Test
    public void testComment() {
        assertEquals("Great course!", review.getComment());
    }

    @Test
    public void testRating() {
        assertEquals(5, review.getRating());
    }

    @Test
    public void testReviewDate() {
        assertNotNull(review.getReviewDate());
    }

    @Test
    public void testUser() {
        assertEquals(user, review.getUser());
    }

    @Test
    public void testCourse() {
        assertEquals(course, review.getCourses());
    }
}