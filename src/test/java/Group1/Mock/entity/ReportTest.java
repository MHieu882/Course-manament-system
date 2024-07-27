package Group1.Mock.entity;

import Group1.Mock.entity.Course;
import Group1.Mock.entity.Report;
import Group1.Mock.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {

    private Report report;

    @BeforeEach
    public void setUp() {
        report = new Report();
    }

    @Test
    public void testReportId() {
        report.setReportId(1L);
        assertEquals(1L, report.getReportId());
    }

    @Test
    public void testReportReason() {
        report.setReportReason("Inappropriate content");
        assertEquals("Inappropriate content", report.getReportReason());
    }

    @Test
    public void testReportDate() {
        Date currentDate = new Date();
        report.setReportDate(currentDate);
        assertTrue(report.getReportDate().equals(currentDate));
    }

    @Test
    public void testUserAssociation() {
        User user = new User();
        user.setId(1L);
        report.setUser(user);
        assertEquals(user, report.getUser());
    }

    @Test
    public void testCourseAssociation() {
        Course course = new Course();
        course.setCourseId(1L);
        report.setCourse(course);
        assertEquals(course, report.getCourse());
    }
}