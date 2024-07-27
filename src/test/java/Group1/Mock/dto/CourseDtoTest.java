package Group1.Mock.dto;

import Group1.Mock.dto.CourseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDtoTest {

    private CourseDto courseDto;

    @BeforeEach
    public void setUp() {
        courseDto = new CourseDto();
    }

    @Test
    public void testNoArgsConstructor() {
        CourseDto dto = new CourseDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long courseId = 1L;
        String courseName = "Java Programming";
        String description = "Learn Java from scratch";
        double price = 99.99;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        boolean isApproved = true;
        String courseLevel = "Beginner";
        String language = "English";
        Set<Long> categoryIds = new HashSet<>();
        Set<Long> instructorIds = new HashSet<>();
        Set<Long> reviewIds = new HashSet<>();

        categoryIds.add(1L);
        instructorIds.add(2L);
        reviewIds.add(3L);

        CourseDto dto = new CourseDto(
                courseId,
                courseName,
                description,
                price,
                createdAt,
                updatedAt,
                isApproved,
                courseLevel,
                language,
                categoryIds,
                instructorIds,
                reviewIds
        );

        assertEquals(courseId, dto.getCourseId());
        assertEquals(courseName, dto.getCourseName());
        assertEquals(description, dto.getDescription());
        assertEquals(price, dto.getPrice());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(updatedAt, dto.getUpdatedAt());
        assertEquals(isApproved, dto.isApproved());
        assertEquals(courseLevel, dto.getCourseLevel());
        assertEquals(language, dto.getLanguage());
        assertEquals(categoryIds, dto.getCategoryIds());
        assertEquals(instructorIds, dto.getInstructorIds());
        assertEquals(reviewIds, dto.getReviewIds());
    }

    @Test
    public void testGettersAndSetters() {
        Long courseId = 1L;
        String courseName = "Java Programming";
        String description = "Learn Java from scratch";
        double price = 99.99;
        Date createdAt = new Date();
        Date updatedAt = new Date();
        boolean isApproved = true;
        String courseLevel = "Beginner";
        String language = "English";
        Set<Long> categoryIds = new HashSet<>();
        Set<Long> instructorIds = new HashSet<>();
        Set<Long> reviewIds = new HashSet<>();

        courseDto.setCourseId(courseId);
        courseDto.setCourseName(courseName);
        courseDto.setDescription(description);
        courseDto.setPrice(price);
        courseDto.setCreatedAt(createdAt);
        courseDto.setUpdatedAt(updatedAt);
        courseDto.setApproved(isApproved);
        courseDto.setCourseLevel(courseLevel);
        courseDto.setLanguage(language);
        courseDto.setCategoryIds(categoryIds);
        courseDto.setInstructorIds(instructorIds);
        courseDto.setReviewIds(reviewIds);

        assertEquals(courseId, courseDto.getCourseId());
        assertEquals(courseName, courseDto.getCourseName());
        assertEquals(description, courseDto.getDescription());
        assertEquals(price, courseDto.getPrice());
        assertEquals(createdAt, courseDto.getCreatedAt());
        assertEquals(updatedAt, courseDto.getUpdatedAt());
        assertEquals(isApproved, courseDto.isApproved());
        assertEquals(courseLevel, courseDto.getCourseLevel());
        assertEquals(language, courseDto.getLanguage());
        assertEquals(categoryIds, courseDto.getCategoryIds());
        assertEquals(instructorIds, courseDto.getInstructorIds());
        assertEquals(reviewIds, courseDto.getReviewIds());
    }
}
