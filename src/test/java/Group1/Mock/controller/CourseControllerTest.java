package Group1.Mock.controller;

import Group1.Mock.dto.CourseDto;
import Group1.Mock.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        courseDto = new CourseDto();
        courseDto.setCourseId(1L);
        courseDto.setCourseName("Test Course");
        courseDto.setPrice(100.0);
    }

    @Test
    void testCreateCourse() {
        when(courseService.createCourse(any(CourseDto.class))).thenReturn(courseDto);

        ResponseEntity<CourseDto> response = courseController.createCourse(courseDto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(courseDto);
        verify(courseService, times(1)).createCourse(any(CourseDto.class));
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseService).deleteCourse(anyLong());

        ResponseEntity<String> response = courseController.deleteCourse(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Course deleted successfully!");
        verify(courseService, times(1)).deleteCourse(anyLong());
    }

    @Test
    void testUpdateCourse() {
        when(courseService.updateCourse(anyLong(), any(CourseDto.class))).thenReturn(courseDto);

        ResponseEntity<CourseDto> response = courseController.updateCourse(1L, courseDto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo(courseDto);
        verify(courseService, times(1)).updateCourse(anyLong(), any(CourseDto.class));
    }

    @Test
//    void testFindAllCourse() {
//        Page<CourseDto> courseList = Collections.singletonList(courseDto);
//        when(courseService.findAllCourse(any())).thenReturn(courseList);
//
//        Page<CourseDto> response = courseController.findAllCourse(0, 10, "courseName");
//
//        assertThat(response).isEqualTo(courseList);
//        verify(courseService, times(1)).findAllCourse(any());
//    }

//    @Test
    void testFindAllCourseByCategoryId() {
//        List<CourseDto> courseList = Collections.singletonList(courseDto);
//        when(courseService.findAllCourseByCategoryId(anyLong())).thenReturn(ResponseEntity.ok(courseList));
//
//        ResponseEntity<List<CourseDto>> response = courseController.findAllCourseByCategoryId(1L);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(courseList);
//        verify(courseService, times(1)).findAllCourseByCategoryId(anyLong());
    }

    @Test
    void testFindCourseById() {
        when(courseService.findCourseById(anyLong())).thenReturn(ResponseEntity.ok(courseDto));

        ResponseEntity<CourseDto> response = courseController.findCourseById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(courseDto);
        verify(courseService, times(1)).findCourseById(anyLong());
    }

    @Test
    void testFindCourseByName() {
        List<CourseDto> courseList = Collections.singletonList(courseDto);
        when(courseService.findCourseByName(anyString())).thenReturn(ResponseEntity.ok(courseList));

        ResponseEntity<List<CourseDto>> response = courseController.findCourseByName("Test Course");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(courseList);
        verify(courseService, times(1)).findCourseByName(anyString());
    }
}
