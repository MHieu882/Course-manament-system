package Group1.Mock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private long reportId;

    private String reportReason;

    private Date reportDate;

    private long userId;

    private long courseId;


}
