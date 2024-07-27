package Group1.Mock.dto;

import Group1.Mock.dto.EnrollmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentDtoTest {

    private EnrollmentDto enrollmentDto;

    @BeforeEach
    public void setUp() {
        enrollmentDto = new EnrollmentDto();
    }

    @Test
    public void testNoArgsConstructor() {
        EnrollmentDto dto = new EnrollmentDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        long enrollmentId = 1L;
        double grade = 95.5;
        boolean status = true;
        Date enrollmentDate = new Date();
        long userId = 2L;
        long courseId = 3L;

        EnrollmentDto dto = new EnrollmentDto(
                enrollmentId,
                grade,
                status,
                enrollmentDate,
                userId,
                courseId
        );

        assertEquals(enrollmentId, dto.getEnrollmentId());
        assertEquals(grade, dto.getGrade());
        assertEquals(status, dto.isStatus());
        assertEquals(enrollmentDate, dto.getEnrollmentDate());
        assertEquals(userId, dto.getUserId());
        assertEquals(courseId, dto.getCourseId());
    }

    @Test
    public void testGettersAndSetters() {
        long enrollmentId = 1L;
        double grade = 95.5;
        boolean status = true;
        Date enrollmentDate = new Date();
        long userId = 2L;
        long courseId = 3L;

        enrollmentDto.setEnrollmentId(enrollmentId);
        enrollmentDto.setGrade(grade);
        enrollmentDto.setStatus(status);
        enrollmentDto.setEnrollmentDate(enrollmentDate);
        enrollmentDto.setUserId(userId);
        enrollmentDto.setCourseId(courseId);

        assertEquals(enrollmentId, enrollmentDto.getEnrollmentId());
        assertEquals(grade, enrollmentDto.getGrade());
        assertEquals(status, enrollmentDto.isStatus());
        assertEquals(enrollmentDate, enrollmentDto.getEnrollmentDate());
        assertEquals(userId, enrollmentDto.getUserId());
        assertEquals(courseId, enrollmentDto.getCourseId());
    }
}
