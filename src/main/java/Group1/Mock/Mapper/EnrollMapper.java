package Group1.Mock.Mapper;

import Group1.Mock.dto.EnrollmentDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Enrollment;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.EnrollmentRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@AllArgsConstructor
public class EnrollMapper {
    private static UserRepository userRepository;
    private static CourseRepository courseRepository;



    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment) {
        return new EnrollmentDto(
                enrollment.getEnrollmentId(),
                enrollment.getGrade(),
                enrollment.isStatus(),
                enrollment.getEnrollmentDate(),
                enrollment.getUser().getId(),
                enrollment.getCourse().getCourseId()
        );
    }

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto) {


        User user = userRepository.findById(enrollmentDto.getUserId()).orElseThrow();
        Course course = courseRepository.findById(enrollmentDto.getCourseId()).orElseThrow();
        return new Enrollment(
                enrollmentDto.getEnrollmentId(),
                enrollmentDto.getGrade(),
                enrollmentDto.isStatus(),
                enrollmentDto.getEnrollmentDate(),
                user,
                course
        );
    }
}
