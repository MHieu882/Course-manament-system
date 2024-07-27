package Group1.Mock.controller;

import Group1.Mock.dto.EnrollmentDto;
import Group1.Mock.entity.Enrollment;
import Group1.Mock.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public EnrollmentDto EnrollCourse(@RequestBody EnrollmentDto enrollmentDto) {
        return enrollmentService.EnrollCourse(enrollmentDto);
    }
}
