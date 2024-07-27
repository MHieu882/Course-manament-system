package Group1.Mock.entity;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.Enrollment;
import Group1.Mock.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentTest {

    private Enrollment enrollment;

    @BeforeEach
    public void setUp() {
        enrollment = new Enrollment();
    }

    @Test
    public void testEnrollmentId() {
        long expectedId = 1;
        enrollment.setEnrollmentId(expectedId);
        assertEquals(expectedId, enrollment.getEnrollmentId());
    }

    @Test
    public void testGrade() {
        double expectedGrade = 85.5;
        enrollment.setGrade(expectedGrade);
        assertEquals(expectedGrade, enrollment.getGrade());
    }

    @Test
    public void testStatus() {
        boolean expectedStatus = true;
        enrollment.setStatus(expectedStatus);
        assertEquals(expectedStatus, enrollment.isStatus());
    }

    @Test
    public void testEnrollmentDate() {
        Date expectedDate = new Date();
        enrollment.setEnrollmentDate(expectedDate);
        assertEquals(expectedDate, enrollment.getEnrollmentDate());
    }

    @Test
    public void testUser() {
        User expectedUser = new User();
        expectedUser.setId(1L);
        enrollment.setUser(expectedUser);
        assertEquals(expectedUser, enrollment.getUser());
    }

    @Test
    public void testCourse() {
        Course expectedCourse = new Course();
        expectedCourse.setCourseId(1L);
        enrollment.setCourse(expectedCourse);
        assertEquals(expectedCourse, enrollment.getCourse());
    }
}