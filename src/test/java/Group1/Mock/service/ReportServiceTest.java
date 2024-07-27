package Group1.Mock.service;

import Group1.Mock.Mapper.ReportMapper;
import Group1.Mock.dto.ReportDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Report;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.ReportRepository;
import Group1.Mock.reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testReportCourse() {
        // Mock data
        ReportDto dto = new ReportDto();
        dto.setReportId(1L);
        dto.setReportReason("Inappropriate content");
        dto.setReportDate(null);
        dto.setCourseId(101L);

        Course course = new Course();
        course.setCourseId(101L);
        User user = new User();
        user.setId(1L);
        Report report = new Report();
        report.setUser(user);
        report.setReportId(1L);
        report.setCourse(course);
        report.setReportReason("Inappropriate content");
        report.setReportDate(null);

        when(authentication.getPrincipal()).thenReturn(user);
        when(courseRepository.findById(dto.getCourseId())).thenReturn(Optional.of(course));
        when(reportRepository.save(any(Report.class))).thenReturn(report);

        // Call the method
        ReportDto result = reportService.ReportCourse(dto);

        // Verify results
        assertNotNull(result);
        assertEquals(dto.getReportId(), result.getReportId());
        assertEquals(dto.getReportReason(), result.getReportReason());
        assertEquals(dto.getReportDate(), result.getReportDate());
        verify(reportRepository).save(any(Report.class));
    }
}
