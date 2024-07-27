package Group1.Mock.service;

import Group1.Mock.Mapper.EnrollMapper;
import Group1.Mock.dto.EnrollmentDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Enrollment;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.EnrollmentRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public EnrollmentDto EnrollCourse(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentDto.getEnrollmentId());
        enrollment.setGrade(enrollmentDto.getGrade());
        enrollment.setStatus(true);
        enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Course course = courseRepository.findById(enrollmentDto.getCourseId()).orElseThrow();

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);
        enrollmentDto.setCourseId(enrollment.getEnrollmentId());
//        return EnrollMapper.mapToEnrollmentDto(enrollmentRepository.save(enrollment));
        return enrollmentDto;

    }

}
