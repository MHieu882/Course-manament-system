package Group1.Mock.controller;

import Group1.Mock.dto.CourseDto;
import Group1.Mock.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/course")
public class CourseController {
    private CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto saveCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(saveCourse, HttpStatus.CREATED);

    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully!");
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseDto courseDto) {
        courseService.updateCourse(courseId, courseDto);
        //fix null
        return new ResponseEntity<>(courseDto, HttpStatus.ACCEPTED);

    }

    @GetMapping("/ListCourse")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public Page<CourseDto> findAllCourse( @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "courseName") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
       return courseService.findAllCourse(pageable);
    }

//tim theo category
    @GetMapping("/courseByCategory/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Page<CourseDto>> findAllCourseByCategoryId(@PathVariable("id") Long categoryId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseService.findAllCourseByCategoryId(categoryId,pageable);
    }
    //tim theo id
    @GetMapping("/courseById")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseDto> findCourseById(@RequestParam("CourseId") Long CourseId) {
        return courseService.findCourseById(CourseId);
    }

    @GetMapping("/courseByName")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<CourseDto>> findCourseByName(@RequestParam("courseName") String courseName) {
        return courseService.findCourseByName(courseName);
    }

}
