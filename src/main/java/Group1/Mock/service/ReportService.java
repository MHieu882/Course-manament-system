package Group1.Mock.service;

import Group1.Mock.Mapper.ReportMapper;
import Group1.Mock.dto.ReportDto;
import Group1.Mock.entity.Course;
import Group1.Mock.entity.Report;
import Group1.Mock.entity.User;
import Group1.Mock.reponsitory.CourseRepository;
import Group1.Mock.reponsitory.ReportRepository;
import Group1.Mock.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public ReportDto ReportCourse(ReportDto dto) {
        Report report = new Report();

        report.setReportId(dto.getReportId());
        report.setReportReason(dto.getReportReason());
        report.setReportDate(dto.getReportDate());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow();

        report.setUser(user);
        report.setCourse(course);
        reportRepository.save(report);
        dto.setReportId(report.getReportId());
        return dto;

    }

}
