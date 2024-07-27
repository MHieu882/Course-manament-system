package Group1.Mock.dto;

import Group1.Mock.dto.ReportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReportDtoTest {

    private ReportDto reportDto;

    @BeforeEach
    public void setUp() {
        reportDto = new ReportDto();
    }

    @Test
    public void testNoArgsConstructor() {
        ReportDto dto = new ReportDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        long reportId = 1L;
        String reportReason = "Inappropriate content";
        Date reportDate = new Date();
        long userId = 2L;
        long courseId = 3L;

        ReportDto dto = new ReportDto(reportId, reportReason, reportDate, userId, courseId);

        assertEquals(reportId, dto.getReportId());
        assertEquals(reportReason, dto.getReportReason());
        assertEquals(reportDate, dto.getReportDate());
        assertEquals(userId, dto.getUserId());
        assertEquals(courseId, dto.getCourseId());
    }

    @Test
    public void testGettersAndSetters() {
        long reportId = 1L;
        String reportReason = "Inappropriate content";
        Date reportDate = new Date();
        long userId = 2L;
        long courseId = 3L;

        reportDto.setReportId(reportId);
        reportDto.setReportReason(reportReason);
        reportDto.setReportDate(reportDate);
        reportDto.setUserId(userId);
        reportDto.setCourseId(courseId);

        assertEquals(reportId, reportDto.getReportId());
        assertEquals(reportReason, reportDto.getReportReason());
        assertEquals(reportDate, reportDto.getReportDate());
        assertEquals(userId, reportDto.getUserId());
        assertEquals(courseId, reportDto.getCourseId());
    }
}
