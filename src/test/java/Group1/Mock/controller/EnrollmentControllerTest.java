package Group1.Mock.controller;

import Group1.Mock.dto.EnrollmentDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.User;
import Group1.Mock.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnrollmentControllerTest {

    @InjectMocks
    private EnrollmentController enrollmentController;

    @Mock
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void EnrollCourse_ShouldReturnEnrollmentDto() {
        // Arrange
        EnrollmentDto enrollmentDto = new EnrollmentDto(1L, 95.0, true, new Date(), 1L, 1L);
        when(enrollmentService.EnrollCourse(any(EnrollmentDto.class))).thenReturn(enrollmentDto);

        // Act
        EnrollmentDto response = enrollmentController.EnrollCourse(enrollmentDto);

        // Assert
        assertEquals(enrollmentDto, response);
    }
}
