package Group1.Mock.service;

import Group1.Mock.Mapper.CategoryMapper;
import Group1.Mock.Mapper.CourseMapper;
import Group1.Mock.dto.CategoryDto;
import Group1.Mock.entity.Category;
import Group1.Mock.entity.Course;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CategoryRepository;
import Group1.Mock.reponsitory.CourseRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {

    private static Logger logger = LoggerFactory.getLogger(CategoryServiceTest.class);

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    private Category category;
    private CategoryDto categoryDto;
    private Set<Course> courses;

    @BeforeAll
    void setUp() {
        logger.info("Setting up");

        MockitoAnnotations.openMocks(this);

        // Initialize test data
        category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Test Category");
        category.setDescription("This is a test category.");

        courses = new HashSet<>();
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Test Course");
        courses.add(course);

        category.setCourses(courses);

        Set<Category> categories = new HashSet<>();
        categories.add(category);
        course.setCategory(categories);

        categoryDto = CategoryMapper.mapToCategoryDto(category);

        categoryService = new CategoryService(courseRepository, categoryRepository);
    }

    @Test
    void testCreateCategory() {
        logger.info("test create category");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);

        assertThat(createdCategory).isNotNull();
        assertThat(createdCategory.getCategoryName()).isEqualTo(categoryDto.getCategoryName());

//        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateCategory() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto updatedCategory = categoryService.updateCategory(1L, categoryDto);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getCategoryName()).isEqualTo(categoryDto.getCategoryName());

//        verify(categoryRepository, times(1)).findById(anyLong());
//        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateCategoryNotFound() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(1L, categoryDto);
        });

//        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @Test
    void testFindAllCategory() {
//        List<Category> categories = new ArrayList<>();
//        categories.add(category);
//
//        Page<Category> categoryPage = new PageImpl<>(categories);
//        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryPage);
//
//        Pageable pageable = PageRequest.of(0, 10);
//        ResponseEntity<List<CategoryDto>> response = categoryService.findAllCategory(pageable);
//
//        assertThat(response.getBody()).isNotEmpty();
//        assertThat(response.getBody().get(0).getCategoryName()).isEqualTo(categoryDto.getCategoryName());
//
////        verify(categoryRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testDeleteCategory() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        doReturn(null).when(courseRepository).save(any(Course.class));
        doNothing().when(categoryRepository).deleteById(anyLong());

        categoryService.DeleteCategory(1L);

//        verify(categoryRepository, times(1)).findById(anyLong());
//        verify(courseRepository, times(courses.size())).save(any(Course.class));
//        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
//
//    @Test
//    void testDeleteCategoryNotFound() {
//        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            categoryService.DeleteCategory(1L);
//        });
//
//        verify(categoryRepository, times(1)).findById(anyLong());
//    }

    @AfterAll
    public void teardown() {
        categoryRepository.delete(category);
        category = null;
        categoryDto = null;
    }
}
