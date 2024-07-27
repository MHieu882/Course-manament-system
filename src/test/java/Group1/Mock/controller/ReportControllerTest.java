package Group1.Mock.controller;

import Group1.Mock.dto.ReportDto;
import Group1.Mock.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock SecurityContext for authentication
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = new User("student", "password", new ArrayList<>()); // Simulate a STUDENT user
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock RequestContextHolder if needed
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    void testReportCourse() {
        // Arrange
        ReportDto inputDto = new ReportDto(1L, "Test Report", new Date(), 1L, 1L);
        ReportDto expectedDto = new ReportDto(1L, "Test Report", new Date(), 1L, 1L);

        when(reportService.ReportCourse(any(ReportDto.class))).thenReturn(expectedDto);

        // Act
        ReportDto result = reportController.ReportCourse(inputDto);

        // Assert
        assertEquals(expectedDto, result);
        verify(reportService, times(1)).ReportCourse(inputDto);
    }
}
