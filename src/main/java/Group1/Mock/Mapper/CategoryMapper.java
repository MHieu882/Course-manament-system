package Group1.Mock.Mapper;

import Group1.Mock.dto.CategoryDto;
import Group1.Mock.entity.Category;
import Group1.Mock.entity.Course;
import Group1.Mock.reponsitory.CourseRepository;

import java.util.*;


public class CategoryMapper {
    private static CourseRepository courseRepository;
    public static CategoryDto mapToCategoryDto(Category category) {
        Set<Long> courseIds=new HashSet<>();

        Set<Course> courses = category.getCourses();

       if(!courses.isEmpty()){
            for(Course course: category.getCourses()){
                courseIds.add(course.getCourseId());
            };
        }
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                courseIds
        );
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        Set<Course> courseSet= new HashSet<>();
        for(Long CourseId: categoryDto.getCourseId()){
            Optional<Course> course=courseRepository.findById(CourseId);

            course.ifPresent(courseSet::add);
        }

        return new Category(
                categoryDto.getCategoryId(),
                categoryDto.getCategoryName(),
                categoryDto.getDescription(),
                courseSet
        );
    }
}
