package Group1.Mock.controller;

import Group1.Mock.dto.CategoryDto;
import Group1.Mock.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto(1L, "Category1", "Description", Collections.emptySet());
        when(categoryService.createCategory(any(CategoryDto.class))).thenReturn(categoryDto);

        // Act
        ResponseEntity<CategoryDto> response = categoryController.createCategory(categoryDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void findAllCategory_ShouldReturnListOfCategories() {
//        // Arrange
//        CategoryDto categoryDto = new CategoryDto(1L, "Category1", "Description", Collections.emptySet());
//        List<CategoryDto> categoryDtos = Collections.singletonList(categoryDto);
//        Pageable pageable = PageRequest.of(0, 10);
//        when(categoryService.findAllCategory(pageable)).thenReturn(ResponseEntity.ok(categoryDtos));
//
//        // Act
//        ResponseEntity<List<CategoryDto>> response = categoryController.findAllCategory(0, 10);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(categoryDtos, response.getBody());
    }

    @Test
    void deleteCategory_ShouldReturnSuccessMessage() {
        // Arrange
        long categoryId = 1L;
        doNothing().when(categoryService).DeleteCategory(categoryId);

        // Act
        ResponseEntity<String> response = categoryController.deleteCategory(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category deleted successfully!", response.getBody());
    }

    @Test
    void updateCategory_ShouldReturnUpdatedCategory() {
        // Arrange
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto(categoryId, "Category1", "Description", Collections.emptySet());
        when(categoryService.updateCategory(eq(categoryId), any(CategoryDto.class))).thenReturn(categoryDto);

        // Act
        ResponseEntity<CategoryDto> response = categoryController.updateCategory(categoryId, categoryDto);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }
}
