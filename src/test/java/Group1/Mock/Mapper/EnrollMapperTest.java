package Group1.Mock.Mapper;


import Group1.Mock.dto.EnrollmentDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Enrollment;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.EnrollmentRepository;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollMapperTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollMapper enrollMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            java.lang.reflect.Field userField = EnrollMapper.class.getDeclaredField("userRepository");
            userField.setAccessible(true);
            userField.set(null, userRepository);

            java.lang.reflect.Field courseField = EnrollMapper.class.getDeclaredField("courseRepository");
            courseField.setAccessible(true);
            courseField.set(null, courseRepository);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMapToEnrollmentDto() {
        // Given
        User user = new User();
        user.setId(1L);

        Course course = new Course();
        course.setCourseId(2L);

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(1L);
        enrollment.setGrade(23.5);
        enrollment.setStatus(true);
        enrollment.setEnrollmentDate(new Date());
        enrollment.setUser(user);
        enrollment.setCourse(course);

        // When
        EnrollmentDto enrollmentDto = EnrollMapper.mapToEnrollmentDto(enrollment);

        // Then
        assertEquals(enrollment.getEnrollmentId(), enrollmentDto.getEnrollmentId());
        assertEquals(enrollment.getGrade(), enrollmentDto.getGrade());
        assertEquals(enrollment.isStatus(), enrollmentDto.isStatus());
        assertEquals(enrollment.getEnrollmentDate(), enrollmentDto.getEnrollmentDate());
        assertEquals(enrollment.getUser().getId(), enrollmentDto.getUserId());
        assertEquals(enrollment.getCourse().getCourseId(), enrollmentDto.getCourseId());
    }

    @Test
    public void testMapToEnrollment() {
        // Given
        User user = new User();
        user.setId(1L);

        Course course = new Course();
        course.setCourseId(2L);

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEnrollmentId(1L);
        enrollmentDto.setGrade(24.5);
        enrollmentDto.setStatus(true);
        enrollmentDto.setEnrollmentDate(new Date());
        enrollmentDto.setUserId(1L);
        enrollmentDto.setCourseId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));

        // When
        Enrollment enrollment = EnrollMapper.mapToEnrollment(enrollmentDto);

        // Then
        assertEquals(enrollmentDto.getEnrollmentId(), enrollment.getEnrollmentId());
        assertEquals(enrollmentDto.getGrade(), enrollment.getGrade());
        assertEquals(enrollmentDto.isStatus(), enrollment.isStatus());
        assertEquals(enrollmentDto.getEnrollmentDate(), enrollment.getEnrollmentDate());
        assertEquals(enrollmentDto.getUserId(), enrollment.getUser().getId());
        assertEquals(enrollmentDto.getCourseId(), enrollment.getCourse().getCourseId());
    }

    @Test
    public void testMapToEnrollment_UserNotFound() {
        // Given
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setUserId(1L);
        enrollmentDto.setCourseId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            EnrollMapper.mapToEnrollment(enrollmentDto);
        });
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    public void testMapToEnrollment_CourseNotFound() {
        // Given
        User user = new User();
        user.setId(1L);

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setUserId(1L);
        enrollmentDto.setCourseId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            EnrollMapper.mapToEnrollment(enrollmentDto);
        });
        assertEquals("No value present", exception.getMessage());
    }
}
