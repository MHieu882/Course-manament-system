package Group1.Mock.Mapper;

import Group1.Mock.dto.ReportDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Report;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportMapper {

    private static UserRepository userRepository;
    private static CourseRepository courseRepository;

    public static ReportDto mapToReportDto(Report report) {
        return new ReportDto(
                report.getReportId(),
                report.getReportReason(),
                report.getReportDate(),
                report.getUser().getId(),
                report.getCourse().getCourseId()
        );
    }

    public static Report mapToReport(ReportDto reportDto) {

        User user = userRepository.findById(reportDto.getUserId()).orElseThrow();
        Course course = courseRepository.findById(reportDto.getCourseId()).orElseThrow();
        return new Report(
                reportDto.getReportId(),
                reportDto.getReportReason(),
                reportDto.getReportDate(),
                user,
                course
        );
    }
}
