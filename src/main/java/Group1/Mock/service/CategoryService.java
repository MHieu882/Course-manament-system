package Group1.Mock.service;

import Group1.Mock.Mapper.CategoryMapper;
import Group1.Mock.dto.CategoryDto;

import Group1.Mock.entity.Category;
import Group1.Mock.entity.Course;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.CategoryRepository;
import Group1.Mock.reponsitory.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private CourseRepository courseRepository;
    private CategoryRepository categoryRepository;
  

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        categoryDto.setCategoryId(category.getCategoryId());
        return categoryDto;
    }



    public CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category not found"+categoryId)
        );
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        return CategoryMapper.mapToCategoryDto(categoryRepository.save(category));
    }

    public ResponseEntity<Page<CategoryDto>>  findAllCategory(Pageable pageable){
        Page<Category> categoryList = categoryRepository.findAll(pageable);

        return ResponseEntity.ok(categoryList.map(CategoryMapper::mapToCategoryDto));

    }
    public void DeleteCategory(Long CategoryId){
        Category category = categoryRepository.findById(CategoryId)
                .orElseThrow(()->new ResourceNotFoundException("Can not find category"+CategoryId));
        //find and delete catelogy at
        for (Course course : category.getCourses()) {
            course.getCategory().remove(category);
            courseRepository.save(course);
        }

        // Sau khi xóa các liên kết, tiến hành xóa danh mục
        categoryRepository.deleteById(CategoryId);

    }

    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
