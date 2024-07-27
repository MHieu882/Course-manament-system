package Group1.Mock.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testCourseConstructor() {
        // Given
        String courseName = "Test Course";
        String description = "This is a test course.";
        double price = 9.99;
        boolean isApproved = true;
        String courseLevel = "Beginner";
        String language = "English";
        Set<Category> categories = Set.of(new Category(1L, "Test Category","vui",null));

        Role role = new Role();
        role.setId(1L); // Assuming ID is 1 for the role

        // Create User object
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("securePassword");
        newUser.setEmail("newUser@example.com");
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());
        newUser.setEnabled(true);
        newUser.setBlocked(false);
        newUser.setRole(role);

        // Create a set of subscriptions (assuming User objects for subscriptions are available)
        Set<User> subscriptions = new HashSet<>();
        // Add subscriptions to the set
        // subscriptions.add(subscription1);
        // subscriptions.add(subscription2);
        newUser.setSubscriptions(subscriptions);

        // Create UserProfile object (assuming UserProfile class exists and is properly defined)
        UserProfile profile = new UserProfile();
        profile.setUser(newUser);
        newUser.setProfile(profile);

        // Create a set of orders (assuming Order objects are available)
        Set<Order> orders = new HashSet<>();
        // Add orders to the set
        // orders.add(order1);
        // orders.add(order2);
        newUser.setOrders(orders);

        Set<User> instructors = Set.of(newUser);
//        Set<Review> reviews = Set.of(new Review(1L, "Great course!", 5,new User());

        // When
        Course course = new Course(null, courseName, description, price, null, null, isApproved, courseLevel, language, categories, instructors, null);

        // Then
        assertNotNull(course);
        assertNull(course.getCourseId());
        assertEquals(courseName, course.getCourseName());
        assertEquals(description, course.getDescription());
        assertEquals(price, course.getPrice(), 0.001);
        assertNull(course.getCreatedAt());
        assertNull(course.getUpdatedAt());
        assertEquals(isApproved, course.isApproved());
        assertEquals(courseLevel, course.getCourseLevel());
        assertEquals(language, course.getLanguage());
        assertEquals(categories, course.getCategory());
        assertEquals(instructors, course.getInstructor());
        assertEquals(null, course.getReviews());
    }
}