package Group1.Mock.service;

import Group1.Mock.Mapper.CourseMapper;

import Group1.Mock.dto.BasicResponse;
import Group1.Mock.dto.CategoryDto;
import Group1.Mock.dto.CourseDto;

import Group1.Mock.entity.*;
import Group1.Mock.exception.GlobalExceptionHandler;
import Group1.Mock.exception.ResourceNotFoundException;
import Group1.Mock.reponsitory.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private CourseRepository courseRepository;
    private ReviewRepository reviewRepository;



    public CourseDto createCourse (CourseDto courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setCourseLevel(courseDTO.getCourseLevel());
        course.setLanguage(courseDTO.getLanguage());

    //set category at course
        Set<Category> categories = new HashSet<>();
        Set<Long> categoryIds = courseDTO.getCategoryIds();
        if (categoryIds != null) {
            for (Long categoryId : categoryIds) {
                Optional<Category> category = categoryRepository.findById(categoryId);
                category.orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + categoryId));
                category.ifPresent(categories::add);
            }
        }
        course.setCategory(categories);

    //set instructors at course
        Set<User> instructors = new HashSet<>();
        Set<Long> instructorIds = courseDTO.getInstructorIds();
        if (instructorIds != null) {
            for(Long userId : instructorIds) {
                Optional<User> user = userRepository.findById(userId);
                user.orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + userId));


                user.ifPresent(u -> {
                    if(u.getRole().getName() == RoleEnum.INSTRUCTOR){
                        instructors.add(u);
                    }
                    else {
                        // Nếu không phải Instructor, ném ngoại lệ
                        throw new AccessDeniedException("User is not an INSTRUCTOR: " +userId);
                    }
                });
            }
        }

        course.setInstructor(instructors);

        courseRepository.save(course);
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCreatedAt(course.getCreatedAt());
//
        return courseDTO;
    }

    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        // Handle categories (if needed)
        List<Category> categories = new ArrayList<>(course.getCategory());
        for (Category category : categories) {
            course.getCategory().remove(category);
            categoryRepository.save(category);
        }

        courseRepository.delete(course);
    }

    public CourseDto updateCourse (Long courseId, CourseDto courseDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new ResourceNotFoundException("Course not found with id: " + courseId)
        );;
        course.setCourseName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setCreatedAt(course.getCreatedAt());
        course.setUpdatedAt(new Date());
        course.setCourseLevel(courseDTO.getCourseLevel());
        course.setLanguage(courseDTO.getLanguage());

        Set<User> instructors = new HashSet<>();
        for (Long instructorId : courseDTO.getInstructorIds()) {
            Optional<User> user= userRepository.findById(instructorId);
            user.orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + instructorId));
            instructors.add(user.get());
        }
        course.setInstructor(instructors);

        Set<Category> categories = new HashSet<>();
        for (Long categoryId : courseDTO.getCategoryIds()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            category.orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + categoryId));
            category.ifPresent(categories::add);
        }
        course.setCategory(categories);

        return CourseMapper.mapToCourseDto(courseRepository.save(course));
    }
    //needtofix
    public Page<CourseDto> findAllCourse(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(CourseMapper::mapToCourseDto);
    }
    //list course by id
    public ResponseEntity<CourseDto> findCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new ResourceNotFoundException("Course not found with id: " + courseId)
        );
        System.out.println("cousre"+course);
        System.out.println("dto"+course);
        CourseDto courseDto = CourseMapper.mapToCourseDto(course);
        return ResponseEntity.ok(courseDto);
    }

    //search course by category
    public ResponseEntity<Page<CourseDto>> findAllCourseByCategoryId(Long categoryId,Pageable pageable) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id: " + categoryId)
        );

        List<CourseDto> coursesList = new ArrayList<>();

        for (Course course : category.getCourses()) {
            CourseDto courseDto = CourseMapper.mapToCourseDto(course);
            coursesList.add(courseDto);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), coursesList.size());
        List<CourseDto> pagedCourseDtos = coursesList.subList(start, end);

        // Tạo Page<CourseDto> từ danh sách đã phân trang
        Page<CourseDto> courseDtoPage = new PageImpl<>(pagedCourseDtos, pageable, coursesList.size());
        return ResponseEntity.ok(courseDtoPage);
    }

    public Optional<Course> searchById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Course saveCourseState(Course c) {
        return courseRepository.save(c);
    }

    //list course by id
    public ResponseEntity<List<CourseDto>> findCourseByName(String courseName) {
        List<Course> courseList = courseRepository.searchByCourseNameLike(courseName);

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDto courseDto = CourseMapper.mapToCourseDto(course);
            courseDtoList.add(courseDto);
        }
        return ResponseEntity.ok(courseDtoList);
    }

}
