package Group1.Mock.Mapper;

import Group1.Mock.dto.CategoryDto;
import Group1.Mock.entity.Category;
import Group1.Mock.entity.Course;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.Mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryMapperTest {

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Set the static field courseRepository in CategoryMapper
        try {
            java.lang.reflect.Field field = CategoryMapper.class.getDeclaredField("courseRepository");
            field.setAccessible(true);
            field.set(null, courseRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMapToCategoryDto() {
        // Given
        Set<Course> courses = new HashSet<>();
        courses.add(new Course(1L, "Course1", "Description1", 100.0, new Date(), new Date(), true, "Beginner", "English", new HashSet<>(), new HashSet<>(), new HashSet<>()));
        Category category = new Category(1L, "Category1", "Description1", courses);

        // When
        CategoryDto categoryDto = CategoryMapper.mapToCategoryDto(category);

        // Then
        assertEquals(category.getCategoryId(), categoryDto.getCategoryId());
        assertEquals(category.getCategoryName(), categoryDto.getCategoryName());
        assertEquals(category.getDescription(), categoryDto.getDescription());
        assertEquals(courses.stream().map(Course::getCourseId).collect(Collectors.toSet()), categoryDto.getCourseId());
    }

    @Test
    public void testMapToCategory() {
        // Given
        Set<Course> courses = new HashSet<>();
        Course course = new Course(1L, "Course1", "Description1", 100.0, new Date(), new Date(), true, "Beginner", "English", new HashSet<>(), new HashSet<>(), new HashSet<>());
        courses.add(course);
        CategoryDto categoryDto = new CategoryDto(1L, "Category1", "Description1", Set.of(1L));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // When
        Category category = CategoryMapper.mapToCategory(categoryDto);

        // Then
        assertEquals(categoryDto.getCategoryId(), category.getCategoryId());
        assertEquals(categoryDto.getCategoryName(), category.getCategoryName());
        assertEquals(categoryDto.getDescription(), category.getDescription());
        assertEquals(courses, category.getCourses());
    }
}
