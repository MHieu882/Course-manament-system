package Group1.Mock.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Mock
    private Course course1;

    @Mock
    private Course course2;

    @InjectMocks
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testManyToManyRelationship_addCourse() {
//        // Given
//        Set<Course> courses = new HashSet<>();
//
//        // When
//        category.getCourses().add(course1); // Assuming addCourse method is not declared in Category class
//        courses.add(course1);
//
//        // Then
//        assertNotNull(category.getCourses());
//        assertEquals(courses, category.getCourses());
//    }

    @Test
    void testManyToManyRelationship_removeCourse() {
        // Given
        Set<Course> courses = new HashSet<>();
        courses.add(course1);
        courses.add(course2);
        category.setCourses(courses);

        // When
        category.getCourses().remove(course1); // Assuming removeCourse method is declared in Category class
        courses.remove(course1);

        // Then
        assertNotNull(category.getCourses());
        assertEquals(courses, category.getCourses());
    }

//    @Test
//    void testManyToManyRelationship_addAndRemoveCourse() {
//        // Given
//        Set<Course> courses = new HashSet<>();
//
//        // When
//        category.addCourse(course1);
//        courses.add(course1);
//        category.addCourse(course2);
//        courses.add(course2);
//        category.removeCourse(course1);
//        courses.remove(course1);
//
//        // Then
//        assertNotNull(category.getCourses());
//        assertEquals(courses, category.getCourses());
//    }

    @Test
    void testManyToManyRelationship_gettersAndSetters() {
        // Given
        Long categoryId = 1L;
        String categoryName = "Test Category";
        String description = "Test Description";

        // When
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryName);
        category.setDescription(description);

        // Then
        assertEquals(categoryId, category.getCategoryId());
        assertEquals(categoryName, category.getCategoryName());
        assertEquals(description, category.getDescription());
    }
}