package Group1.Mock.controller;

import Group1.Mock.dto.ReportDto;
import Group1.Mock.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/report")
    public ReportDto ReportCourse(@RequestBody ReportDto reportDto) {
        return reportService.ReportCourse(reportDto);
    }
}
