package Group1.Mock.dto;

import Group1.Mock.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDtoTest {

    private CategoryDto categoryDto;

    @BeforeEach
    public void setUp() {
        categoryDto = new CategoryDto();
    }

    @Test
    public void testNoArgsConstructor() {
        CategoryDto dto = new CategoryDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        Long categoryId = 1L;
        String categoryName = "Programming";
        String description = "Courses related to programming languages";
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(101L);
        courseIds.add(102L);

        CategoryDto dto = new CategoryDto(categoryId, categoryName, description, courseIds);

        assertEquals(categoryId, dto.getCategoryId());
        assertEquals(categoryName, dto.getCategoryName());
        assertEquals(description, dto.getDescription());
        assertEquals(courseIds, dto.getCourseId());
    }

    @Test
    public void testGettersAndSetters() {
        Long categoryId = 1L;
        String categoryName = "Programming";
        String description = "Courses related to programming languages";
        Set<Long> courseIds = new HashSet<>();
        courseIds.add(101L);
        courseIds.add(102L);

        categoryDto.setCategoryId(categoryId);
        categoryDto.setCategoryName(categoryName);
        categoryDto.setDescription(description);
        categoryDto.setCourseId(courseIds);

        assertEquals(categoryId, categoryDto.getCategoryId());
        assertEquals(categoryName, categoryDto.getCategoryName());
        assertEquals(description, categoryDto.getDescription());
        assertEquals(courseIds, categoryDto.getCourseId());
    }
}
